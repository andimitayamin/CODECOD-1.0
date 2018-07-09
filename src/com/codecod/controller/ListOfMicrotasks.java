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

		List<MicrotaskModel> listMicroTask = new ArrayList<>();
		try {  
			ResultSet rs = connection.executeTake("SELECT `task`.*, `microtask`.* FROM `task` INNER JOIN `microtask` ON `task`.`path` = `microtask`.`path` WHERE `requester_id` = '"+id+"' ");
			BlockStmt statement = new BlockStmt();

			while (rs.next()) {
				statement.addStatement(rs.getString("method_body"));
				listMicroTask.add(new MicrotaskModel(rs.getString("method_id"),rs.getString("declaration"),rs.getString("method_name"), statement, rs.getString("path")));
			}

			request.setAttribute("mtasks", listMicroTask);
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
