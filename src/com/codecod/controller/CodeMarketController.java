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

import com.codecod.QualityControl.MajorityVotingModel;
import com.codecod.connection.MySQLConnection;
import com.codecod.model.ClazzModel;
import com.codecod.model.MicrotaskModel;
import com.github.javaparser.ast.stmt.BlockStmt;

/**
 * Servlet implementation class CodeMarketController
 */
@WebServlet("/CodeMarketController")
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
		HttpSession session = request.getSession(false);
		String id = "";
		
		if(session!=null) {
			id=(String)session.getAttribute("username");  
		}
		
		MySQLConnection connection = MySQLConnection.getInstance();
		


		List<ClazzModel> clazzList = new ArrayList<>();
		List<MicrotaskModel> listMicroTask = new ArrayList<>();
		List <MajorityVotingModel> listMV = new ArrayList<>();
		
		try {
			//show microtask (type:class)
			ResultSet rs = connection.executeTake("select `clazzID`,`task`.`path`,`file_name` from `clazz_microtask` INNER JOIN `task` ON `clazz_microtask`.`path` = `task`.`path` GROUP BY `path` ");

			while (rs.next()) {
				String path = rs.getString("path");
				String fileName = path.substring(path.lastIndexOf("\\")+1);
				
				ClazzModel clazz = new ClazzModel();
				clazz.setClazzName(fileName);
				clazz.setClazzID(rs.getString("clazzID"));
				clazz.setPath(rs.getString("path"));
				clazz.setTaskName(rs.getString("file_name"));
							 
				clazzList.add(clazz);
			}
			
//			Show microtask (type:single method)			
			ResultSet RSmethod = connection.executeTake("SELECT * FROM microtask WHERE method_id NOT IN (SELECT microtaskID FROM detected_smell WHERE workerID = '"+id+"')");
				
			while(RSmethod.next()) {
				MicrotaskModel microtask = new MicrotaskModel();
				
				microtask.setMicrotaskID(RSmethod.getString("method_id"));
				microtask.setMethodName(RSmethod.getString("method_name"));
				
				listMicroTask.add(microtask);	
			}
				
			//show MVlist
			ResultSet MV = connection.executeTake("SELECT DISTINCT * FROM `majority_vote` GROUP BY `microtaskID` ");
			
			while(MV.next()) {
				
				MajorityVotingModel majVot = new MajorityVotingModel();
				majVot.setMicrotaskID(MV.getString("microtaskID"));
				
				//listMV.add(new MajorityVotingModel(MV.getString("microtaskID"), MV.getString("smell_name"), MV.getInt("line"), MV.getInt("votes")));			
			
				listMV.add(majVot);
			}
			
			request.setAttribute("microtaskList", listMicroTask);
			request.setAttribute("classList", clazzList);
			request.setAttribute("listVote", listMV);
			
			

			
			getServletContext().getRequestDispatcher("/microtask_market.jsp").forward(request, response);
			
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

	}
}
