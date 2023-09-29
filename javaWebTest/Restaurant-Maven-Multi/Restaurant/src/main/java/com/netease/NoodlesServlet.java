package com.netease;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.netease.Kitchen;
/**
 * Servlet implementation class NoodlesServlet
 */
public class NoodlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger m_log =  Logger.getLogger(NoodlesServlet.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoodlesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String vegetable = request.getParameter("vegetable");
		String noodles = Kitchen.makeNoodles(vegetable);
		writer.println(noodles);
		m_log.debug("Noodles Now Start");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
