package de.niklasmehner;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.HttpHeaders;

@WebServlet("/hello")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SomeBean someBean;
	
	@javax.ws.rs.core.Context
	private HttpHeaders headers;

	@javax.ws.rs.core.Context
	private HttpServletRequest request;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintStream out = new PrintStream(response.getOutputStream());
		try {
			someBean.hello(request.getParameter("name"));		
			out.println("Hello from Servlet: " + headers.getAcceptableLanguages() + " " + Collections.list(request.getLocales()));
		} catch (ConstraintViolationException e) {
			out.println(e.getConstraintViolations().iterator().next().getMessage());
		}
	}


}
