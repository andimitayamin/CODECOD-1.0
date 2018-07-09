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
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			ResultSet url = connection.executeTake("SELECT `path` FROM `task` WHERE `file_name` LIKE '"+taskName+"%' AND `requester_id` = '"+id+"'");
			while(url.next()) {
				
				String urls = url.getString("path").replace("\\", "\\\\");
				ResultSet methods = connection.executeTake("SELECT DISTINCT `method_name`,`method_id`, COUNT(DISTINCT `workerId`)AS `workers` FROM `microtask` "
																	+ "LEFT JOIN `detected_smell` ON `microtask`.`method_id` = `detected_smell`.`microtaskID`"
																	+ " WHERE `path` = '"+urls+"' GROUP BY `method_id`");
				while(methods.next()) {
					
					MicrotaskModel methodDetails = new MicrotaskModel();
					methodDetails.setPathFile(url.getString("path"));
					
					methodDetails.setMethodName(methods.getString("method_name"));
					methodDetails.setMicrotaskID(methods.getString("method_id"));
					methodDetails.setNumOfWorker(methods.getInt("workers"));
					
					methodsDetails.add(methodDetails);
					System.out.println(methodsDetails.size());
				}				
				
			}
			   
			request.setAttribute("details", methodsDetails);
			
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
