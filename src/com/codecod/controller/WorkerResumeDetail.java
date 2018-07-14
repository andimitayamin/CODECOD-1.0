package com.codecod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.codecod.connection.MySQLConnection;

/**
 * Servlet implementation class WorkerResumeDetail
 */
@WebServlet("/WorkerResumeDetail")
public class WorkerResumeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerResumeDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
//		String action = request.getPathInfo();
//		String answerID = action.substring(1, action.length());
//		
//		HttpSession session = request.getSession(false);
//		String id;
//		if(session!=null) {
//			id = (String)session.getAttribute("username");
//		}
		
		String microtaskID = request.getParameter("microtaskID");
		PrintWriter out = response.getWriter();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			ResultSet rs = connection.executeTake("select * from detected_smell where microtaskID = '"+microtaskID+"'");
			while(rs.next()) {
				String smell = rs.getString("smell_name");
				int line = rs.getInt("line");
				out.write(smell);
				out.write(line);
				System.out.println(smell+" di"+line);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
