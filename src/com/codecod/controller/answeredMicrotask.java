package com.codecod.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.answeredMicrotaskModel;

/**
 * Servlet implementation class answeredMicrotask
 */
@WebServlet("/answeredMicrotask")
public class answeredMicrotask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answeredMicrotask() {
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
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String smell = request.getParameter("hiddenSmellIni");
		String line = request.getParameter("hiddenSmellLine"); 
		String microtaskID = request.getParameter("microtaskID");
		String suggestedRefactoring = request.getParameter("suggestedRefactoring");
		
		HttpSession session = request.getSession(false);
		String id = null; 
		if(session!=null) {
			id = (String)session.getAttribute("username");
		}
				
		List<String> smells =  Arrays.asList(smell.split("\\s*,\\s*")); 
		List<String> lines = Arrays.asList(line.split("\\s*,\\s*"));
		answeredMicrotaskModel saveNewRecord = new answeredMicrotaskModel();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		String answer_ID = "";
		
		for(int i = 0; i<smells.size(); i++) {
			
			
			try {
				//cek apakah jawabannya sudah ada tersimpan di tabel detected_smell
				ResultSet checkAnswer = connection.executeTake("SELECT `answerID` FROM `detected_smell` WHERE `microtaskID` = '"+microtaskID+"' AND `smell_name` = '"+smells.get(i)+"' AND `line` = "+lines.get(i)+" ");
				
				if(checkAnswer.next()) {
					//jika ada, vote nya ditambah 1
					//answerID = nya di ambil dr DB

					answer_ID = checkAnswer.getString("answerID");
					connection.executeStore(String.format("UPDATE `detected_smell` SET `vote`=`vote`+1 WHERE `answerID` = '%s'", answer_ID));				
					
				} else { 
					//jika tidak ada, answer id nya di generate baru.
					Random rand = new Random();
					int randomNum = rand.nextInt(500);
					answer_ID = "ans_"+microtaskID+"_"+randomNum;	
					connection.executeStore(String.format("INSERT INTO `detected_smell`(`answerID`, `microtaskID`, `smell_name`, `line`, `vote`) VALUES ('%s','%s','%s','%s',1)",answer_ID, microtaskID, smells.get(i), lines.get(i)));
					
				} 
				  
				saveNewRecord.setAnswerID(answer_ID);
				saveNewRecord.setWorkerID(id);
				saveNewRecord.setSuggestedRefactoring(suggestedRefactoring);
				connection.executeStore(saveNewRecord.addWorkerHistory());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorpage.html");
			}
			
		}
		
		response.sendRedirect("CodeMarket");
		
	}

}
