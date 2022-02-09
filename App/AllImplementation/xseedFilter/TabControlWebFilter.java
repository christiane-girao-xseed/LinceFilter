package xseedFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/*
 * Configuracao de url-pattern efetuada no web.xml
 */
//@WebFilter(filterName = "TabControlWebFilter")
@WebFilter("/*")
public class TabControlWebFilter implements Filter {

	private enum Message {
		TIMEOUT("SESSÃO EXPIRADA"), DUPLICATED_SESSION("SESSÃO DUPLICADA");
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
	public static final String SYNC_TOKEN = "__syncToken";
	public static final String MESSAGE_URL_PARAM = "message";

	private static final int TIMEOUT = 10;
	private static final String LINCE = "/servlet/LOGON";
	private static final String ERROR_HANDLER_SERVLET_MAP = "errorHandler";
	private static final String ERROR_HANDLER_PATH = "/servlet/" + ERROR_HANDLER_SERVLET_MAP;

	private static final Set<String> PASS_THROUGH = new HashSet<>(
			Arrays.asList(new String[] {  ERROR_HANDLER_PATH }));

	private static final Logger logger = LogManager.getLogger(TabControlWebFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		boolean doChain = true;
		HttpServletRequest httpRequest = null;
		HttpServletResponse httpResponse = null;

		TabControlHttpRequestWrapper tabRequest = null;
		TabControlHttpResponseWrapper tabResponse = null;

		String logonPreference = null;
/*
		String syncTokenSession = null;
		String syncTokenPage = null;*/

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

			if (checkPassThrough(httpRequest.getServletPath())) {  
				if (httpRequest.getHeader(FOREIGN_UUID) != null) {
					logger.info("Recebendo UUID externo {}", httpRequest.getHeader(FOREIGN_UUID));

					ThreadContext.put(USER_TAB_ID,
							httpRequest.getHeader(FOREIGN_UUID) + "," + ThreadContext.get(USER_TAB_ID));
				} else {
					logger.warn("Requisicao sem UUID externo");
					ThreadContext.put(USER_TAB_ID, "unknown" + "," + ThreadContext.get(USER_TAB_ID));
				}

				executeFilterChain(chain, request, response);
			} else {
				if (httpRequest.getMethod().equals(AppHttpMethodFilter.HTTP_GET)) {
					if (httpRequest.getServletPath().equals(LINCE) ) {
						logonPreference = httpRequest.getServletPath();
						if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null
								|| httpRequest.getParameter(TIMESTAMP_URL_PARAM) == null) {
							logger.info("Redirecionamento: Abertura sistema");
							doChain = false;
						} else {
							if (Instant.now().getEpochSecond()
									- Long.parseLong(httpRequest.getParameter(TIMESTAMP_URL_PARAM)) > TIMEOUT) {
								logger.info("Redirecionamento: Timestamp vencido");
								doChain = false;
							} else {
								tabRequest = new TabControlHttpRequestWrapper(httpRequest,
										httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
								tabResponse = new TabControlHttpResponseWrapper(httpResponse,
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
								tabRequest = new TabControlHttpRequestWrapper(httpRequest,
										httpRequest.getParameter(TAB_CONTROL_URL_PARAM));

								if (tabRequest.getSession().isNew()) {
									logger.info("Redirecionamento: Sessao expirada - GET");
									message = Message.TIMEOUT;
									doChain = false;
								} else {
									tabResponse = new TabControlHttpResponseWrapper(httpResponse,
											httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
								}
							}
						}
					}
				} else if (httpRequest.getMethod().equals(AppHttpMethodFilter.HTTP_POST)) {
					if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) == null) {
						logger.info("Redirecionamento: Chamada POST sem UUID");
						doChain = false;
					} else {
						tabRequest = new TabControlHttpRequestWrapper(httpRequest,
								httpRequest.getParameter(TAB_CONTROL_URL_PARAM));
						tabResponse = new TabControlHttpResponseWrapper(httpResponse,
								httpRequest.getParameter(TAB_CONTROL_URL_PARAM));

						/*syncTokenSession = (String) tabRequest.getSession().getAttribute(SYNC_TOKEN);
						syncTokenPage = tabRequest.getParameter(SYNC_TOKEN);

						if (syncTokenSession == null) {
							logger.info("Redirecionamento: Sessao expirada - POST");
							message = Message.TIMEOUT;
							doChain = false;
						} else if (syncTokenPage == null) {
							logger.info("Redirecionamento: Page sem SYNC_TOKEN");
							doChain = false;
						} else if (syncTokenSession.equals(syncTokenPage) == false) {
							logger.info("Redirecionamento: SYNC_TOKEN invalido");
							message = Message.DUPLICATED_SESSION;
							doChain = false;
						} else if (((TabControlHttpSessionImpl) tabRequest.getSession()).getRequestId() == null) {
							logger.info("Redirecionamento: UUID invalido");
							doChain = false;
						}*/
					}
				}

				if (doChain) {
					ThreadContext.put(USER_TAB_ID,
							((TabControlHttpSessionImpl) tabRequest.getSession()).getRequestId());
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
			}
		} catch (Exception e) {
			//logger.error(TabControlErrorHandler.GURU_MEDITATION, e);
			httpResponse.sendRedirect(
					ERROR_HANDLER_SERVLET_MAP + "?" + TAB_CONTROL_URL_PARAM + "=" + ThreadContext.get(USER_TAB_ID));
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

	private boolean checkPassThrough(String servletPath) {
		return PASS_THROUGH.contains(servletPath);
	}

	private String generateRedirect(HttpServletRequest httpRequest, String logonPreference, Message message)
			throws Exception {
		String fullQueryString = null;
		String servletLogon = null;
		String[] keyValue = null;
     	servletLogon = "LOGON";
	
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

		/*if (httpRequest.getParameter(TAB_CONTROL_URL_PARAM) != null) {
			if (new TabControlHttpRequestWrapper(httpRequest, httpRequest.getParameter(TAB_CONTROL_URL_PARAM))
					.getSession().getAttribute("VC5090") != null) {
				servletLogon += "&VC5090";
			}
		}*/

		return servletLogon;
	}

}
