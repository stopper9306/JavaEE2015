package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {   
		HttpSession session  = request.getSession();
		if(session != null)
			try {      
				session.removeAttribute("logonSessData");
				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath() + "/index.html");          
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

}
