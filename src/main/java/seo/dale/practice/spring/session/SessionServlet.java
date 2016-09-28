package seo.dale.practice.spring.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author Dale Seo
 */
@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		out.printf("- %s : %s%n", "ID", session.getId());
		out.printf("- %s : %s%n", "New", session.isNew());
		out.printf("- %s : %s%n", "Max Inactive Interval", session.getMaxInactiveInterval());
		out.printf("- %s : %s%n", "Creation Time", new Date(session.getCreationTime()));
		out.printf("- %s : %s%n", "Last Accessed Time", new Date(session.getLastAccessedTime()));

		String name = req.getParameter("name");
		String value = req.getParameter("value");
		session.setAttribute(name, value);
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String attributeName = enumeration.nextElement();
			out.printf("\t- %s : %s%n", attributeName, session.getAttribute(attributeName));
		}
	}

}
