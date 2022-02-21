package xseedFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControlHttpRequestWrapper extends UploadReadyRequestWrapper {

	private ControlHttpSessionImpl tabControlSession = null;
	private String requestId = null;

	public ControlHttpRequestWrapper(HttpServletRequest request, String requestId) throws Exception {
		super(request);

		this.requestId = requestId;
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (create) {
			return getSession();
		} else {
			if (tabControlSession != null) {
				return getSession();
			} else {
				return null;
			}
		}
	}

	@Override
	public HttpSession getSession() {
		if (tabControlSession == null) {
			tabControlSession = new ControlHttpSessionImpl(super.getSession(), requestId);
		}

		return tabControlSession;
	}
}
