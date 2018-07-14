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
import com.codecod.model.answeredMicrotaskModel;

/**
 * Servlet implementation class WorkerResume
 */
@WebServlet("/WorkerResume")
public class WorkerResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerResume() {
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
		
		List<answeredMicrotaskModel> mtaskList = new ArrayList<>();
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			ResultSet microtaskList = connection.executeTake("SELECT * FROM `worker_history` INNER JOIN detected_smell USING (answerID) WHERE workerID ='"+id+"' GROUP BY `microtaskID`");

			while(microtaskList.next()) {
				answeredMicrotaskModel microtask = new answeredMicrotaskModel();
				
				if(microtaskList.getString("microtaskID").startsWith("clazz")) {
					ResultSet microtaskName = connection.executeTake("SELECT path FROM clazz_microtask where clazzID = '"+microtaskList.getString("microtaskID")+"'");
					if(microtaskName.next()) {
						String path = microtaskName.getString("path");
						String fileName = path.substring(path.lastIndexOf("\\")+1);
						
						microtask.setMicrotaskName(fileName);
					}
				}else {
					ResultSet microtaskName = connection.executeTake("SELECT method_name FROM microtask where method_id= '"+microtaskList.getString("microtaskID")+"'");
					if(microtaskName.next()) {					
						microtask.setMicrotaskName(microtaskName.getString("method_name")+"()");
					}
				} 
				
				microtask.setMicrotaskID(microtaskList.getString("microtaskID"));
				microtask.setAnswerID(microtaskList.getString("answerID"));
				microtask.setName(microtaskList.getString("smell_name"));
				microtask.setLine(microtaskList.getString("line"));
						
				mtaskList.add(microtask);			
			}
			
			request.setAttribute("microtasks", mtaskList);
			getServletContext().getRequestDispatcher("/WorkerResume.jsp").forward(request, response);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
