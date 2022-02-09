package xseedFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionContext;

public class TabControlHttpSessionImpl implements HttpSession {

	private static final String REQUEST_ID = "__REQUEST_ID";
	private HttpSession session = null;
	private String requestId = null;
	private boolean isNew = false;

	public TabControlHttpSessionImpl(HttpSession session, String requestId) {
		if (requestId == null) {
			throw new RuntimeException("Parametro requestId nao pode ser nulo");
		}

		this.session = session;
		this.requestId = requestId;

		this.session.setAttribute(REQUEST_ID, requestId);

		if (getAttributeNames().hasMoreElements() == false) {
			isNew = true;
		}
	}

	public String getRequestId() {
		return (String) session.getAttribute(REQUEST_ID);
	}

	@Override
	public long getCreationTime() {
		return session.getCreationTime();
	}

	@Override
	public String getId() {
		return session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return session.getLastAccessedTime();
	}

	@Override
	public ServletContext getServletContext() {
		return session.getServletContext();
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		session.setMaxInactiveInterval(interval);
	}

	@Override
	public int getMaxInactiveInterval() {
		return session.getMaxInactiveInterval();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return session.getSessionContext();
	}

	@Override
	public Object getAttribute(String name) {
		return session.getAttribute(requestId + "->" + name);
	}

	@Override
	public Object getValue(String name) {
		return session.getValue(requestId + "->" + name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		String attribute = null;
		List<String> requestIdAttributeNames = null;
		Enumeration<String> originalAttributeNames = null;

		requestIdAttributeNames = new LinkedList<>();
		originalAttributeNames = session.getAttributeNames();

		while (originalAttributeNames.hasMoreElements()) {
			attribute = originalAttributeNames.nextElement();

			if (attribute.startsWith(requestId)) {
				requestIdAttributeNames.add(attribute.substring(
						attribute.indexOf(requestId) + requestId.length() + "->".length(), attribute.length()));
			}
		}

		return Collections.enumeration(requestIdAttributeNames);
	}

	@Override
	public String[] getValueNames() {
		return Arrays.asList(getAttributeNames()).toArray(new String[0]);
	}

	@Override
	public void setAttribute(String name, Object value) {
		session.setAttribute(requestId + "->" + name, value);
	}

	@Override
	public void putValue(String name, Object value) {
		session.putValue(requestId + "->" + name, value);
	}

	@Override
	public void removeAttribute(String name) {
		session.removeAttribute(requestId + "->" + name);
	}

	@Override
	public void removeValue(String name) {
		session.removeValue(requestId + "->" + name);
	}

	@Override
	public void invalidate() {
		session.invalidate();
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
