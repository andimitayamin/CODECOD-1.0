package com.codecod.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecod.connection.MySQLConnection;

/**
 * Servlet implementation class ChangeMicrotaskState
 */
@WebServlet("/ChangeMicrotaskState")
public class ChangeMicrotaskState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeMicrotaskState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String action = request.getPathInfo();
//		String microtaskID = action.substring(1, action.length());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String currentState = request.getParameter("status");
		String microtaskID = request.getParameter("microtaskID");
		
				
		String nextState;
		if(currentState.equals("OPEN")) {
			nextState = "CLOSED";
		}else {
			nextState = "OPEN";
		}
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			String microtaskType;
			String idType;
			
			if(microtaskID.startsWith("clazz")) {
				microtaskType = "clazz_microtask";
				idType = "clazzID";
			}else {
				microtaskType = "microtask";
				idType = "method_id";
			}
			
			connection.executeStore(String.format("UPDATE `%s` SET `status`='%s' WHERE `%s` = '%s'", microtaskType, nextState, idType, microtaskID ));
			
			response.sendRedirect("result/"+microtaskID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
