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

import com.codecod.connection.MySQLConnection;
import com.codecod.model.ClazzModel;
import com.codecod.model.MicrotaskModel;
import com.codecod.model.TaskModel;
import com.github.javaparser.ast.stmt.BlockStmt;

/**
 * Servlet implementation class CodeMarketController
 */
@WebServlet("/CodeMarket")
public class CodeMarketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CodeMarketController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MySQLConnection connection = MySQLConnection.getInstance();
		

		List<MicrotaskModel> listMicroTask = new ArrayList<>();
		try {
			ResultSet rs = connection.executeTake("select * from microtask");
			BlockStmt statement = new BlockStmt();

			while (rs.next()) {
				statement.addStatement(rs.getString("method_body"));
				listMicroTask.add(new MicrotaskModel(rs.getString("method_id"),rs.getString("declaration"),rs.getString("method_name"), statement, rs.getString("path")));
			}

			request.setAttribute("microtasks", listMicroTask);
			getServletContext().getRequestDispatcher("/microtask_market.jsp").forward(request, response);
		} catch (SQLException e) {
			response.sendRedirect("errorpage.html");
		}		
		
//		List <String> listTask = new ArrayList<>();
//		try {
//			ResultSet rsTask = connection.executeTake("select * from task");
//			
//			if(rsTask.next()) {
//				TaskModel taski = new TaskModel();
//				taski.setTaskName(rsTask.getString("file_name"));
//				listTask.add(taski.getTaskName());			
//
//				request.setAttribute("taski", listTask);
//				getServletContext().getRequestDispatcher("/microtask_market.jsp").forward(request, response);
//			}
//		} catch (SQLException e) {
//			response.sendRedirect("errorpage.html");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	// private void ListMicrotask (HttpServletRequest request, HttpServletResponse
	// response)
	// throws SQLException, IOException, ServletException {
	//
	// List<MicrotaskModel> microtasks = microtaskDAO.this;
	// }

}
