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
import com.codecod.model.MicrotaskModel;
import com.github.javaparser.ast.stmt.BlockStmt;

/**
 * Servlet implementation class MicrotaskController
 */
@WebServlet("/MicrotaskController")
public class MicrotaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MicrotaskController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		String microtaskID = action.substring(1, action.length());

		MySQLConnection connection = MySQLConnection.getInstance();
		MicrotaskModel microTask = new MicrotaskModel();
		BlockStmt statement = new BlockStmt();

		try {
			
			if(microtaskID.startsWith("clazz")) {
				ResultSet rs = connection.executeTake(String.format("SELECT * FROM clazz_microtask WHERE clazzID = '%s'", microtaskID));	
				if (rs.next()) {
					
					String path = rs.getString("path");
					String fileName = path.substring(path.lastIndexOf("\\")+1);
					microTask.setMethodName(fileName);
					
//					statement.addStatement(rs.getString("clazz_body"));
					microTask.setClassBody(rs.getString("clazz_body"));

					microTask.setMicrotaskID(microtaskID);
					
					request.setAttribute("microtaskClazz", microTask);
					getServletContext().getRequestDispatcher("/workspaceClazz.jsp").forward(request, response);
										
				}
	
			}else {
				ResultSet rs = connection.executeTake(String.format("SELECT * FROM microtask WHERE method_id = '%s'", microtaskID));
	
				if (rs.next()) {
					statement.addStatement(rs.getString("method_body"));
					microTask = new MicrotaskModel(rs.getString("method_id"),rs.getString("declaration"),rs.getString("method_name"), statement, rs.getString("path"));
					

					request.setAttribute("microtask", microTask);
					getServletContext().getRequestDispatcher("/workspace.jsp").forward(request, response);
				}
			}
			

			
		} catch (SQLException e) {
			response.sendRedirect("errorpage.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
