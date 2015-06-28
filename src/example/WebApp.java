package example;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebApp extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(200);
		final ArrayList arr=new ArrayList();
	    arr.add("asdsaas");
	    arr.add("asdsaas2");
	    arr.add("asdsaas3");
	    //Here we serialize the stream to a String.
	    final String output = arr.toString();
	    resp.setContentLength(output.length());
	    //And write the string to output.
	    resp.getOutputStream().write(output.getBytes());
	    resp.getOutputStream().flush();
	    resp.getOutputStream().close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getParameter(arg0)
		super.doPost(req, resp);
	}
	
	
	
}
