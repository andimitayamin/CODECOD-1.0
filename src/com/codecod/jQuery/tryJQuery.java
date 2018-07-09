package com.codecod.jQuery;

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
 * Servlet implementation class tryJQuery
 */
@WebServlet("/tryJQuery")
public class tryJQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tryJQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

//	     PrintWriter out = response.getWriter();
//	     String country = request.getParameter("country");
//	     String idnya = request.getParameter("id");
//	     
//		MySQLConnection connection = MySQLConnection.getInstance();	
//
//	     
//	     try {
//			connection.executeStore(String.format("UPDATE `user` SET `name`='%s'WHERE `id` = '1234'", country));
//			System.out.println(idnya);
//		     out.print("Data saved successfully....!!");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String id = request.getParameter("id");
	     String name = request.getParameter("name");
	     
	     System.out.println(id);
	     System.out.println(name);
	}

}
