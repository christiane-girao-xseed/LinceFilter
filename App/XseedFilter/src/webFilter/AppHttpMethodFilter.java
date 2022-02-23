package webFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Configurar ordem de execucao no web.xml
 */
@WebFilter(filterName = "AppHttpMethodFilter")
public class AppHttpMethodFilter implements Filter {

	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";

	private static final Logger logger = LogManager.getLogger(AppHttpMethodFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equals(HTTP_GET) || httpRequest.getMethod().equals(HTTP_POST)) {
			chain.doFilter(request, response);
		} else {
			logger.warn("HTTP Method: {}", httpRequest.getMethod());
			logger.warn(httpRequest.getRemoteAddr());

			for (String headerName : java.util.Collections.list(httpRequest.getHeaderNames())) {
				logger.warn(headerName + ": {}", httpRequest.getHeader(headerName));
			}
		}
	}
}
