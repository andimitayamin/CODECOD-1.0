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
		List<MajorityVotingModel> OAlist = new ArrayList();
		List<MajorityVotingModel> MVlist = new ArrayList();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			ResultSet OA = connection.executeTake(String.format("select * from detected_smell where microtaskID = '%s'", microtaskID));
			while(OA.next()) {
				MajorityVotingModel outAgree = new MajorityVotingModel();
				outAgree.setSmell(OA.getString("smell_name"));
				outAgree.setLine(OA.getInt("line"));
				outAgree.setAgreement(OA.getInt("vote"));
				
				OAlist.add(outAgree);
			}
			
			ResultSet MV = connection.executeTake(String.format("SELECT DISTINCT `voteUp`,`voteDown`,`microtaskID`,`smell_name`,`line` FROM"
					+ " (SELECT `microtaskID`,`smell_name`,`line` FROM `majority_vote` WHERE `microtaskID` = '%s') AS `mv`"
					+ " INNER JOIN (SELECT COUNT(`vote`) AS `voteUp` FROM `majority_vote` WHERE `vote` = 1 AND `microtaskID` = '%s')AS `PartVoteUp` "
					+ " INNER JOIN (SELECT COUNT(`vote`) AS `voteDown` FROM `majority_vote` WHERE `vote` = -1 AND `microtaskID` = '%s')AS `PartVoteDown` "
					+ "GROUP BY `microtaskID` ", microtaskID,microtaskID,microtaskID));
			
			while(MV.next()) {
				MajorityVotingModel majVot = new MajorityVotingModel();
				majVot.setSmell(MV.getString("smell_name"));
				majVot.setLine(MV.getInt("line"));
				majVot.setVote_up(MV.getInt("voteUp"));
				majVot.setVote_down(MV.getInt("voteDown"));
				
				MVlist.add(majVot);
			}
			
			//disini utk tampilkan hasil QC
			request.setAttribute("OutAgree", OAlist);
			request.setAttribute("MajVot", MVlist);
			
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
