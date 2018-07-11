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
import com.codecod.model.answeredMicrotaskModel;
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
		
		MicrotaskModel microTask = new MicrotaskModel();
		List<answeredMicrotaskModel> voting = new ArrayList();
		
		MySQLConnection connection = MySQLConnection.getInstance();	
		try {
			if(microtaskID.startsWith("clazz")) {
					ResultSet rs = connection.executeTake(String.format("SELECT * FROM clazz_microtask WHERE clazzID = '%s'", microtaskID));
					
					if (rs.next()) {
						String path = rs.getString("path");
						String fileName = path.substring(path.lastIndexOf("\\")+1);
						
						microTask.setMethodName(fileName);
						microTask.setClassBody(rs.getString("clazz_body"));
						microTask.setMicrotaskID(microtaskID);				
											
					}
				
			}else{
				
					ResultSet rs = connection.executeTake(String.format("SELECT * FROM microtask WHERE method_id = '%s'", microtaskID));
					BlockStmt statement = new BlockStmt();

					if (rs.next()) {
						statement.addStatement(rs.getString("method_body"));
						microTask = new MicrotaskModel(rs.getString("method_id"),rs.getString("declaration"),rs.getString("method_name"), statement, rs.getString("path"));
						
					}
			}
				
			ResultSet MV = connection.executeTake(String.format("SELECT * FROM detected_smell WHERE microtaskID ='%s'", microtaskID));
				
				while(MV.next()) {
					answeredMicrotaskModel MVM= new answeredMicrotaskModel();
					MVM.setMicrotaskID(microtaskID);
					MVM.setAnswerID(MV.getString("answerID"));
					MVM.setName(MV.getString("smell_name"));
					MVM.setLine(MV.getString("line"));
					
					voting.add(MVM);					
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
