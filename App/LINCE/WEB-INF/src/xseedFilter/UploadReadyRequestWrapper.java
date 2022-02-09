package xseedFilter;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class UploadReadyRequestWrapper extends HttpServletRequestWrapper {
	private boolean isMultipart;
	private ServletRequestContext requestContext;

	private FileItemFactory itemFactory;
	private ServletFileUpload fileUpload;
	private List<?> items;

	/**
	 * @throws Exception Return an exception if occurs an error.
	 */
	public UploadReadyRequestWrapper(HttpServletRequest httpServletRequest) throws Exception {
		super(httpServletRequest);

		requestContext = new ServletRequestContext(httpServletRequest);
		isMultipart = ServletFileUpload.isMultipartContent(requestContext);

		if (isMultipart) {
			itemFactory = new DiskFileItemFactory();
			fileUpload = new ServletFileUpload(itemFactory);
			items = fileUpload.parseRequest(requestContext);

			isMultipart = true;
		} else {
			isMultipart = false;
		}
	}

	/**
	 * Gets the value of the parameter based on the parameter name.
	 *
	 * @param parameterName specifies the parameter.
	 * @return parameterValue.
	 */
	@Override
	public String getParameter(String parameterName) {
		if (isMultipart) {
			Iterator<?> iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();

				if (fileItem.getFieldName().equals(parameterName)) {
					return fileItem.getString();
				}
			}
		}

		return super.getParameter(parameterName);
	}

	/**
	 * Gets the bytes of the file based on the parameter name.
	 *
	 * @param parameterName specifies the parameter.
	 * @return fileBytes.
	 */
	public byte[] getFileBytes(String parameterName) {
		byte[] fileBytes = null;

		if (isMultipart) {
			Iterator<?> iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();

				if (fileItem.getFieldName().equals(parameterName)) {
					fileBytes = fileItem.get();
				}
			}
		}

		return fileBytes;
	}

	/**
	 * Gets the name of the file based on the parameter name.
	 *
	 * @param parameterName specifies the parameter.
	 * @return fileName.
	 */
	public String getFileName(String parameterName) {
		String fileName = null;

		if (isMultipart) {
			Iterator<?> iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();

				if (fileItem.getFieldName().equals(parameterName)) {
					fileName = fileItem.getName();
				}
			}
		}

		if (fileName != null) {
			if (getHeader("user-agent").indexOf("Windows") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
			} else if (getHeader("user-agent").indexOf("Linux") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}
		}

		return fileName;
	}

	/**
	 * Gets the content type of the specified parameter name.
	 *
	 * @param parameterName specifies the parameter.
	 * @return contentType
	 */
	public String getContentType(String parameterName) {
		String contentType = null;

		if (isMultipart) {
			Iterator<?> iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();

				if (fileItem.getFieldName().equals(parameterName)) {
					contentType = fileItem.getContentType();
				}
			}
		}

		return contentType;
	}

	public boolean isMultipart() {
		return isMultipart;
	}

	public ServletRequestContext getRequestContext() {
		return requestContext;
	}

	public FileItemFactory getItemFactory() {
		return itemFactory;
	}

	public ServletFileUpload getFileUpload() {
		return fileUpload;
	}

	public List<?> getItems() {
		return items;
	}
}
