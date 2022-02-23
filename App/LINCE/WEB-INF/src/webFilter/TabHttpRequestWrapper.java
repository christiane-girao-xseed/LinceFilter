package webFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TabHttpRequestWrapper extends UploadReadyRequestWrapper {

	private TabHttpSessionImpl tabControlSession = null;
	private String requestId = null;

	public TabHttpRequestWrapper(HttpServletRequest request, String requestId) throws Exception {
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
			tabControlSession = new TabHttpSessionImpl(super.getSession(), requestId);
		}

		return tabControlSession;
	}
}
