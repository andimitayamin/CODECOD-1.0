package com.codecod.QualityControl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecod.connection.MySQLConnection;

/**
 * Servlet implementation class vote
 */
@WebServlet("/vote")
public class vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	      
		
		 String smell = request.getParameter("smell");
	     String line = request.getParameter("line");
	     String microtaskID = request.getParameter("id");
	     String type = request.getParameter("type");
	     		
		MySQLConnection connection = MySQLConnection.getInstance();	
		
		int value=1;
	     if(type.equals("down"))
	     {
	    	 value = -1;
	     }
	     
    	 try {
				connection.executeStore(String.format("UPDATE `majority_vote` SET `votes`=`votes`+'%d' WHERE `microtaskID`='%s' AND `smell_name`='%s' AND `line`='%s'",value, microtaskID, smell, line));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
