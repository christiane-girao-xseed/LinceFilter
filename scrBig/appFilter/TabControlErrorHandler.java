package appFilter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/errorHandler")
public class TabControlErrorHandler extends HttpServlet {

	public static final String GURU_MEDITATION = "Guru Meditation";
	private static final long serialVersionUID = 2977482230898219473L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.write("<html>\r\n" + "<head>\r\n" + "<title> SAD - " + GURU_MEDITATION + "</title>\r\n"
				+ "<style>\r\n" + "error {\r\n" + "  border: 8px solid #FFFFFF ;\r\n" + "  position: absolute;\r\n"
				+ "  left: 50%;\r\n" + "  top: 50%;\r\n" + "  transform: translate(-50%, -50%);\r\n"
				+ "  width: 50% ;\r\n" + "  min-width: 800px;\r\n" + "  text-align:center ;\r\n"
				+ "  font: bold 1.5em/2em monospace ;\r\n" + "  color: #ff0000 ;\r\n"
				+ "  background-color: #FFFFFF ;\r\n" + "  animation: blink .55s step-end infinite alternate;\r\n"
				+ "}\r\n" + "</style>\r\n" + "<body>\r\n" + "<error>\r\n"
				+ "    Falha no SAD.&nbsp;&nbsp;&nbsp;&nbsp; Guru Meditation "
				+ request.getParameter(TabControlWebFilter.TAB_CONTROL_URL_PARAM) + "\r\n" + "</error>\r\n"
				+ "</body>\r\n" + "</html>");

		printWriter.flush();
		printWriter.close();
	}

}
