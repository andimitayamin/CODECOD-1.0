package com.codecod.requester;

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

/**
 * Servlet implementation class TaskPageDetail
 */
@WebServlet("/TaskPageDetail")
public class TaskPageDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskPageDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getPathInfo();
		String taskName = action.substring(1, (action.length()-4));
		
		HttpSession session = request.getSession(false);
		String id = null;
		if(session!=null) {
			id = (String)session.getAttribute("username");
		}
		

		List<MicrotaskModel> methodsDetails = new ArrayList<>();
		List<MicrotaskModel> classDetails = new ArrayList<>();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			ResultSet url = connection.executeTake("SELECT `path` FROM `task` WHERE `file_name` LIKE '"+taskName+"%' AND `requester_id` = '"+id+"'");
			while(url.next()) {
				
				String urls = url.getString("path").replace("\\", "\\\\");
								
				//Find class as microtask
				ResultSet classes = connection.executeTake("SELECT * FROM clazz_microtask INNER JOIN `task` USING (`path`) WHERE `path` ='"+urls+"' AND `requester_id`='"+id+"'");
				
				while(classes.next()) {
					
					MicrotaskModel classDetail = new MicrotaskModel();
					classDetail.setPathFile(url.getString("path"));
					
					String name = classes.getString("path");
					String className= name.substring(name.lastIndexOf("\\")+1);
					
					classDetail.setMethodName(className);
					classDetail.setMicrotaskID(classes.getString("clazzID"));
//					classDetail.setNumOfWorker(classes.getInt("vote"));
					
					classDetails.add(classDetail);

				}	
				
				//Find method as microtask
//				ResultSet methods = connection.executeTake("SELECT * FROM microtask INNER JOIN detected_smell ON detected_smell.microtaskID = microtask.method_id WHERE path ='"+urls+"'");
				ResultSet methods = connection.executeTake("SELECT `method_name`,`method_id` FROM microtask INNER JOIN `task` USING (`path`) WHERE `path` ='"+urls+"' AND `requester_id`='"+id+"'");
				while(methods.next()) {
					
					MicrotaskModel methodDetail = new MicrotaskModel();
					
					methodDetail.setPathFile(url.getString("path"));
					methodDetail.setMethodName(methods.getString("method_name"));
					methodDetail.setMicrotaskID(methods.getString("method_id"));
//					methodDetail.setNumOfWorker(methods.getInt("vote"));
					
					methodsDetails.add(methodDetail);

				}
				 
			}
			   
			request.setAttribute("class_details", classDetails);
			request.setAttribute("method_details", methodsDetails);
			
			getServletContext().getRequestDispatcher("/task.jsp").forward(request, response);
			
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
