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
import com.codecod.model.TaskModel;

/**
 * Servlet implementation class ListPageController
 */
@WebServlet("/ListPageController")
public class ListPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPageController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String id = null; 
		if(session!=null) {
			id = (String)session.getAttribute("username");
		}

		List <TaskModel> listOfTask = new ArrayList<>();
		MySQLConnection connection = MySQLConnection.getInstance();
		try {
			
			ResultSet getTaskInfo = connection.executeTake(String.format("SELECT DISTINCT `file_name` AS `file_name`, COUNT(DISTINCT `path`) as `paths` FROM `task` WHERE `requester_id` = '%s' group by `file_name` ",id));
			while(getTaskInfo.next()) {
				TaskModel task = new TaskModel();
				String taskName = getTaskInfo.getString("file_name").substring(0, (getTaskInfo.getString("file_name").length()-4));
				task.setTaskName(taskName);
				task.setNumOfTask(getTaskInfo.getInt("paths"));
				listOfTask.add(task);

			}
			
			request.setAttribute("list", listOfTask);
			getServletContext().getRequestDispatcher("/listOfMicrotask.jsp").forward(request, response);
			
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

		
	}

}
