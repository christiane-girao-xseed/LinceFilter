package webFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TabHttpResponseWrapper extends HttpServletResponseWrapper {

	private static String LOGIN = "";

	private String requestId = null;
	private String location = null;
	
	public TabHttpResponseWrapper(HttpServletResponse response, String requestId) {		
		super(response);
		LOGIN =TabWebFilter.startupPage;
		this.requestId = requestId;	
	}

	@Override
	public void sendRedirect(String location) {
		this.location = location;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return super.getOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return super.getWriter();
	}

	public boolean sendRedirect() {
		if (location == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getLocation() {
		if (location.equalsIgnoreCase(LOGIN)) {
			return location + "?" + TabWebFilter.TAB_CONTROL_URL_PARAM + "=" + requestId + "&"
					+ TabWebFilter.TIMESTAMP_URL_PARAM + "=" + Instant.now().getEpochSecond();
		} else {
			if (location.indexOf("?")==-1)
			{
				return location + "?" + TabWebFilter.TAB_CONTROL_URL_PARAM + "=" + requestId;
			}
			else
			{
				return location + "&" + TabWebFilter.TAB_CONTROL_URL_PARAM + "=" + requestId;
			}
		}
	}
}
