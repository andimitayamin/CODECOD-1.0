package com.codecod.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.UserModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "LoginController", urlPatterns = { "/LoginController" })
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
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");

		MySQLConnection connection = MySQLConnection.getInstance();
		UserModel user = null;
		try {
			ResultSet rs = connection.executeTake(UserModel.selectByEmailAndPassword(email, passwd));
			if (rs.next()) {
				user = new UserModel();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setRole(rs.getString("role"));
				
				request.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/success.jsp").forward(request,response);
			} else {
				response.sendRedirect("errorpage.html");
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public String toString() {
		return "UserLoginController []";
	}

}
