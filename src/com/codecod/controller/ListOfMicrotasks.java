package com.codecod.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.MicrotaskModel;
import com.github.javaparser.ast.stmt.BlockStmt;

/**
 * Servlet implementation class ListOfMicrotasks
 */
@WebServlet("/ListOfMicrotasks")
public class ListOfMicrotasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOfMicrotasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String id = null; 
		if(session!=null) {
			id = (String)session.getAttribute("username");
		}
		
		MySQLConnection connection = MySQLConnection.getInstance();	
		List<MicrotaskModel> listMethodAsMicrotask = new ArrayList<>();
		List<MicrotaskModel> listClassAsMicrotask = new ArrayList<>();
		
		try {  
			//1. semua method
			ResultSet rs = connection.executeTake("SELECT `method_id`,`method_name`,`requester_id` FROM `microtask` INNER JOIN `task` USING (`path`) WHERE `method_id` IN (SELECT microtaskID FROM detected_smell) AND requester_id = '"+id+"'");

			while (rs.next()) {
				
				MicrotaskModel methodAsMtask = new MicrotaskModel();
				methodAsMtask.setMicrotaskID(rs.getString("method_id"));
				methodAsMtask.setMethodName(rs.getString("method_name"));
				
				listMethodAsMicrotask.add(methodAsMtask);
				
			}
			
			//2. semua class
			ResultSet sets = connection.executeTake("SELECT `clazzID`,`path`,`requester_id` FROM `clazz_microtask` INNER JOIN `task` USING (`path`) WHERE `clazzID` IN (SELECT `microtaskID` FROM `detected_smell`) AND `requester_id` = '"+id+"'");
			while (sets.next()) {
				
				MicrotaskModel classAsMtask = new MicrotaskModel();
				String name = sets.getString("path");
				String className = name.substring(name.lastIndexOf("\\")+1);
				
				
				
				classAsMtask.setMicrotaskID(sets.getString("clazzID"));
				classAsMtask.setMethodName(className);
				
				listClassAsMicrotask.add(classAsMtask);
				
			}

			request.setAttribute("methodTasks", listMethodAsMicrotask);
			request.setAttribute("classTasks", listClassAsMicrotask);
			
			getServletContext().getRequestDispatcher("/listMicrotaskByRequester.jsp").forward(request, response);
		} catch (SQLException e) {
			response.sendRedirect("errorpage.html");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
