package webFilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/*
 * Configuracao de url-pattern efetuada no web.xml
 */


/*
 * web.xml
 * <?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xsi:schemaLocation="http://JAVA.sun.com/xml/ns/javaee 
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	      version="3.0"
          metadata-complete="false">	  
    <filter>    
    <filter-name>TabWebFilter</filter-name>  
    <filter-class>webFilter.TabWebFilter</filter-class>  
      <init-param>  
          <param-name>startupPage</param-name>  
          <param-value>LOGON</param-value>  
      </init-param>
       <init-param>  
          <param-name>timeout</param-name>  
          <param-value>100</param-value>  
      </init-param>
    </filter>  
  
  	<filter-mapping>
  		<filter-name>AppHttpMethodFilter</filter-name>
  		<url-pattern>/servlet/*</url-pattern>
  	</filter-mapping>  	
  	
    <filter-mapping>
  		<filter-name>TabWebFilter</filter-name>
  		<url-pattern>/servlet/*</url-pattern>
  	</filter-mapping>
 </web-app>
 */
@WebFilter(filterName = "TabWebFilter")
public class TabWebFilter implements Filter {

	private enum Message {
		TIMEOUT("SESSÃO EXPIRADA");
		private String message = null;

		Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
	public static final String TIMESTAMP_URL_PARAM = "timestamp";
	public static final String TAB_CONTROL_URL_PARAM = "tabId";
	public static final String FOREIGN_UUID = "foreign-uuid";
	public static final String USER_TAB_ID = "userTabId";
	public static final String MESSAGE_URL_PARAM = "message";
	
	
	private static int timeout = 100;
	public static String startupPage="";
	private static  String startURL = "";
	
	private static final Logger logger = LogManager.getLogger(TabWebFilter.class);
	
//	/https://www.javatpoint.com/filter-config
	FilterConfig config; 
	
	public void init(FilterConfig config) throws ServletException {  
	    this.config=config;  
	}  
	  
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		boolean doChain = true;
		
		 startupPage=config.getInitParameter("startupPage");  
         if (startupPage.isEmpty() == false)
         {
        	 startURL = "/servlet/" + startupPage;      			  
         }
         else
         {
        	 startURL = "/servlet/LOGON";   
         }
         String timeoutStr=config.getInitParameter("timeout");  
         if (timeoutStr.isEmpty() == false)
         {
        	 timeout=  Integer.parseInt(timeoutStr);      			  
         }
		 HttpServletRequest httpRequest = null;
		HttpServletResponse httpResponse = null;

		TabHttpRequestWrapper tabRequest = null;
		TabHttpResponseWrapper tabResponse = null;

		String logonPreference = null;

		Message message = null;

		try {
			httpRequest = (HttpServletRequest) request;
			httpResponse = (HttpServletResponse) response;

			if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null) {
				ThreadContext.put(USER_TAB_ID, UUID.randomUUID().toString());
			} else {
				ThreadContext.put(USER_TAB_ID, httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
			}

			logger.info("URL: {} {}?{}", httpRequest.getMethod(), httpRequest.getServletPath(),
					httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "");

			
				if (httpRequest.getMethod().equals(AppHttpMethodFilter.HTTP_GET)) {
					if (httpRequest.getServletPath().equals(startURL) ) {
						logonPreference = httpRequest.getServletPath();
						if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null
								|| httpRequest.getParameter(TIMESTAMP_URL_PARAM) == null) {
							logger.info("Redirecionamento: Abertura sistema");
							doChain = false;
						} else {
							if (Instant.now().getEpochSecond()
									- Long.parseLong(httpRequest.getParameter(TIMESTAMP_URL_PARAM)) > timeout) {
								logger.info("Redirecionamento: Timestamp vencido");
								doChain = false;
							} else {
								tabRequest = new TabHttpRequestWrapper(httpRequest,
										httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
								tabResponse = new TabHttpResponseWrapper(httpResponse,
										httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
							}
						}
					} else {
						if (httpRequest.getHeader("referer") == null || httpRequest.getHeader("referer").equals("")) {
							logger.info("Redirecionamento: Chamada sem referer");
							doChain = false;
						} else {
							if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null) {
								logger.info("Redirecionamento: Chamada GET sem UUID");
								doChain = false;
							} else {
								tabRequest = new TabHttpRequestWrapper(httpRequest,
										httpRequest.getParameter(TAB_CONTROL_URL_PARAM));

								if (tabRequest.getSession().isNew()) {
									logger.info("Redirecionamento: Sessao expirada - GET");
									message = Message.TIMEOUT;
									doChain = false;
								} else {
									tabResponse = new TabHttpResponseWrapper(httpResponse,
											httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
								}
							}
						}
					}
				} else if (httpRequest.getMethod().equals(AppHttpMethodFilter.HTTP_POST)) {
					
					if (Instant.now().getEpochSecond()
							- Long.parseLong(httpRequest.getParameter(TIMESTAMP_URL_PARAM)) > timeout) {
						logger.info("Redirecionamento: Timestamp vencido");
						doChain = false;
					}
					else if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null) {
						logger.info("Redirecionamento: Chamada POST sem UUID");
						doChain = false;
					} else {
						tabRequest = new TabHttpRequestWrapper(httpRequest,
								httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
						tabResponse = new TabHttpResponseWrapper(httpResponse,
								httpRequest.getParameter(TAB_CONTROL_URL_PARAM));						
					}
				}

				if (doChain) {
					ThreadContext.put(USER_TAB_ID,
							((TabHttpSessionImpl) tabRequest.getSession()).getRequestId());
					executeFilterChain(chain, tabRequest, tabResponse);
					if (tabResponse.sendRedirect()) {
						httpResponse.sendRedirect(tabResponse.getLocation());
						logger.info("Response: {} {}", httpResponse.getStatus(), tabResponse.getLocation());
					}
				} else {
					String redirectURL = generateRedirect(httpRequest, logonPreference, message);
					httpResponse.sendRedirect(redirectURL);
					logger.info("Response Seguranca: {} {}", httpResponse.getStatus(), redirectURL);
				}
			
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}

	private void executeFilterChain(FilterChain chain, ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		Instant begin = null;
		begin = Instant.now();
		chain.doFilter(request, response);
		logExecutionTime(((HttpServletRequest) request).getServletPath(), begin, Instant.now());
	}

	private void logExecutionTime(String servletPath, Instant begin, Instant end) {
		logger.info("Tempo processamento para {}: {} milissegundos", servletPath,
				ChronoUnit.MILLIS.between(begin, end));
	}

	
	private String generateRedirect(HttpServletRequest httpRequest, String logonPreference, Message message)
			throws Exception {
		String fullQueryString = null;
		String servletLogon = null;
		String[] keyValue = null;
     	servletLogon = startupPage; //"LOGON";
	
		servletLogon += "?" + TAB_CONTROL_URL_PARAM + "=" + UUID.randomUUID().toString() + "&" + TIMESTAMP_URL_PARAM
				+ "=" + Instant.now().getEpochSecond();

		if (message != null) {
			servletLogon += "&" + MESSAGE_URL_PARAM + "="
					+ Base64.getEncoder().encodeToString(message.getMessage().getBytes());
		}

		fullQueryString = httpRequest.getQueryString();

		if (fullQueryString != null) {
			for (String query : fullQueryString.split("&")) {
				keyValue = query.split("=", 2);

				if (keyValue[0].equals(TAB_CONTROL_URL_PARAM) == false
						&& keyValue[0].equals(TIMESTAMP_URL_PARAM) == false) {
					servletLogon += "&" + query;
				}
			}
		}
		return servletLogon;
	}
	

}
