package com.codecod.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.UserModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");

		MySQLConnection connection = MySQLConnection.getInstance();
		RequestDispatcher page = null;
		

		try {
			ResultSet rs = connection.executeTake(UserModel.selectByEmailAndPassword(email, passwd));

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("username", rs.getString("id"));
				
				if (rs.getString("role").equals("Worker")) {
					response.sendRedirect("CodeMarket");
					
				} else {
					page = getServletContext().getRequestDispatcher("/dashboard.jsp");
					page.forward(request, response);
				}

				session.setAttribute("role", rs.getString("role"));
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
