package com.codecod.QualityControl;

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
import com.codecod.model.MicrotaskModel;
import com.github.javaparser.ast.stmt.BlockStmt;

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
		
		String action = request.getPathInfo();
		String microtaskID = action.substring(1, action.length());			
		
		MicrotaskModel microTask = null;
		List<MajorityVotingModel> voting = new ArrayList();
		
		MySQLConnection connection = MySQLConnection.getInstance();	
		try {
				
					ResultSet rs = connection.executeTake(String.format("SELECT * FROM microtask WHERE method_id = '%s'", microtaskID));
					BlockStmt statement = new BlockStmt();

					if (rs.next()) {
						statement.addStatement(rs.getString("method_body"));
						microTask = new MicrotaskModel(rs.getString("method_id"),rs.getString("declaration"),rs.getString("method_name"), statement, rs.getString("path"));
						
					}
					
					ResultSet MV = connection.executeTake(String.format("SELECT * FROM majority_vote WHERE microtaskID ='%s'", microtaskID));
					while(MV.next()) {
						voting.add(new MajorityVotingModel(MV.getString("microtaskID"), MV.getString("smell_name"), MV.getInt("line"), MV.getInt("votes")));
					}
					
					request.setAttribute("majvot", voting);
					request.setAttribute("microtask", microTask);
					
					getServletContext().getRequestDispatcher("/VotingPage.jsp").forward(request, response);
			
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
