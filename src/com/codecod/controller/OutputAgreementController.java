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

import com.codecod.QualityControl.MajorityVotingModel;
import com.codecod.connection.MySQLConnection;
import com.codecod.model.MicrotaskModel;
import com.codecod.model.answeredMicrotaskModel;

/**
 * Servlet implementation class OutputAgreementController
 */
@WebServlet("/OutputAgreementController")
public class OutputAgreementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutputAgreementController() {
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
		
		MicrotaskModel microtask = new MicrotaskModel();
		
		List<MajorityVotingModel> OAlist = new ArrayList<>();
		List<MajorityVotingModel> MVlist = new ArrayList<>();
		List<answeredMicrotaskModel> suggest = new ArrayList<>();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			String microtaskType;
			String idType;
			
			if(microtaskID.startsWith("clazz")) {
				microtaskType = "clazz_microtask";
				idType = "clazzID";
			}else {
				microtaskType = "microtask";
				idType = "method_id";
			}
			
			ResultSet getState = connection.executeTake(String.format("select status from `%s` where `%s` = '%s' ", microtaskType,idType,microtaskID));
			if(getState.next()) {
				microtask.setStatus(getState.getString("status"));
				microtask.setMicrotaskID(microtaskID);
			}	
			
			
			ResultSet OA = connection.executeTake(String.format("select * from detected_smell where microtaskID = '%s'", microtaskID));
			while(OA.next()) {
								
				MajorityVotingModel outAgree = new MajorityVotingModel();
				outAgree.setSmell(OA.getString("smell_name"));
				outAgree.setLine(OA.getInt("line"));
				outAgree.setAgreement(OA.getInt("vote"));
				
				OAlist.add(outAgree);
			}
			
			
			ResultSet MV = connection.executeTake(String.format("select distinct smell_name, line from majority_vote where microtaskID = '%s'",microtaskID));
			
			while(MV.next()) {
				MajorityVotingModel majVot = new MajorityVotingModel();
				majVot.setSmell(MV.getString("smell_name"));
				majVot.setLine(MV.getInt("line"));
				
				ResultSet getVote = connection.executeTake(String.format("SELECT vote_up, vote_down FROM `majority_vote` "
						+ "INNER JOIN (SELECT COUNT(vote) as vote_up from majority_vote WHERE microtaskID = '%s' AND smell_name = '%s' and line = '%s' AND vote = 1)as partOne "
						+ "INNER JOIN (SELECT COUNT(vote) as vote_down from majority_vote WHERE microtaskID = '%s' AND smell_name = '%s' and line = '%s' AND vote = -1)as partTwo "
						+ "WHERE `microtaskID`='%s' ", microtaskID, MV.getString("smell_name"), MV.getString("line") , microtaskID, MV.getString("smell_name"), MV.getString("line"),microtaskID));
				if(getVote.next()) {
					majVot.setVote_up(getVote.getInt("vote_up"));
					majVot.setVote_down(getVote.getInt("vote_down"));
					
				}
				
				
				MVlist.add(majVot);
			}
			
			ResultSet suggestRef = connection.executeTake(String.format("SELECT distinct suggested_refactoring FROM `worker_history` WHERE answerID IN "
									+ "(SELECT answerID FROM detected_smell WHERE microtaskID = '%s')", microtaskID));
			while(suggestRef.next()) {
				answeredMicrotaskModel suggestRefactoring = new answeredMicrotaskModel();
				suggestRefactoring.setSuggestedRefactoring(suggestRef.getString("suggested_refactoring"));
				suggest.add(suggestRefactoring);
			}
			
			//disini utk tampilkan hasil QC
			request.setAttribute("OutAgree", OAlist);
			request.setAttribute("MajVot", MVlist);
			request.setAttribute("suggested",suggest);
			request.setAttribute("state",microtask);
			
			getServletContext().getRequestDispatcher("/showQC.jsp").forward(request, response);
			
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
		doGet(request, response);
	}

}
