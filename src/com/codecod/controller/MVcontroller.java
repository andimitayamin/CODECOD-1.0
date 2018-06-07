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
import com.codecod.model.MVmodel;

/**
 * Servlet implementation class MVcontroller
 */
@WebServlet("/MVcontroller")
public class MVcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String acceptance;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MVcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
MySQLConnection connection = MySQLConnection.getInstance();
		
		List<MVmodel> result_list = new ArrayList<>();
		
		try {
			ResultSet rs = connection.executeTake("SELECT `microtask`,`name`,`answered`,`jumWor` FROM "
					+ "(SELECT `microtaskID` AS `microtask`,`name`,COUNT(DISTINCT `workerID`)AS `answered` "
					+ "FROM `detected_smell` GROUP BY `microtaskID`,`name`) AS `firstPart` "
					+ "INNER JOIN (SELECT `microtaskID`, COUNT(DISTINCT `workerID`) AS `jumWor` FROM `detected_smell`GROUP BY `microtaskID` ) AS `secondPart` "
					+ "ON `firstPart`.`microtask` = `secondPart`.`microtaskID` ORDER BY `microtaskID` ");
			
			while(rs.next()) {
				result_list.add(new MVmodel(rs.getString("microtask"),rs.getString("name"),this.checkValidity(rs.getInt("answered"),rs.getInt("jumWor"))));
			}
			request.setAttribute("result_list", result_list);
			getServletContext().getRequestDispatcher("/showQC.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String checkValidity(int counter, int total_worker) {
		int halfValue = total_worker/2;
		if((total_worker % 2) == 0) {
			if(((total_worker % counter) < halfValue)&&(counter != halfValue)) {
				this.acceptance = "accepted";
			}else {
				this.acceptance = "considered";
			}
		}else {
			if((total_worker % counter) <= halfValue) {
				this.acceptance = "accepted";;
			}else {
				this.acceptance = "considered";
			}
		}
		return acceptance;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
