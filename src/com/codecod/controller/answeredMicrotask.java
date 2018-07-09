package com.codecod.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.answeredMicrotaskModel;

/**
 * Servlet implementation class answeredMicrotask
 */
@WebServlet("/answeredMicrotask")
public class answeredMicrotask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answeredMicrotask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String smell = request.getParameter("hiddenSmellIni");
		String line = request.getParameter("hiddenSmellLine"); 
		String microtaskID = request.getParameter("microtaskID");
		 
		HttpSession session = request.getSession(false);
		String id = null; 
		if(session!=null) {
			id = (String)session.getAttribute("username");
		}
				
		List<String> smells =  Arrays.asList(smell.split("\\s*,\\s*")); 
		List<String> lines = Arrays.asList(line.split("\\s*,\\s*"));
		
		MySQLConnection connection = MySQLConnection.getInstance();
		

		
		for(int i = 0; i<smells.size(); i++) {

			answeredMicrotaskModel answer = new answeredMicrotaskModel(microtaskID, smells.get(i), lines.get(i), id);
			
			try {
				connection.executeStore(answer.save_answer());
				
//				response.sendRedirect("CodeMarket");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorpage.html");
			}
			
		}
		
	}

}
