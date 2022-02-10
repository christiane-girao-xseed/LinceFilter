package xseedFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TabControlHttpResponseWrapper extends HttpServletResponseWrapper {

	private static final String LOGIN = "LOGON";

	private String requestId = null;
	private String location = null;

	public TabControlHttpResponseWrapper(HttpServletResponse response, String requestId) {
		super(response);

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
			return location + "?" + ControlWebFilter.TAB_CONTROL_URL_PARAM + "=" + requestId + "&"
					+ ControlWebFilter.TIMESTAMP_URL_PARAM + "=" + Instant.now().getEpochSecond();
		} else {
			return location + "?" + ControlWebFilter.TAB_CONTROL_URL_PARAM + "=" + requestId;
		}
	}
}
