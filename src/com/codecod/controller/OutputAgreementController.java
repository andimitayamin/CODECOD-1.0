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
		List<MajorityVotingModel> OA = new ArrayList();
		List<MajorityVotingModel> MV = new ArrayList();
		
		MySQLConnection connection = MySQLConnection.getInstance();
		
		try {
			//terjadi 3 kali query.
			//query 1 : cari  line of code nya yang tertinggi.
			//query 2 : cari jumlah num of workernya
			//query 3 : cek jumlah code smell yg line dan smell nya sama (di for kan sesuai jumlah LOC)
			// bandingkan jumlah query 3 dgn query 1
			
			ResultSet lines = connection.executeTake(String.format("SELECT DISTINCT `line`, `limit_worker` FROM `detected_smell` INNER JOIN (SELECT `num_of_worker` AS `limit_worker` FROM `task`) AS `workers` WHERE `microtaskID` = '%s' ORDER BY `line` DESC ",microtaskID));
			if(lines.next()) {
				
				for(int i = 1; i<=lines.getInt("line");i++ ) {
				ResultSet rs = connection.executeTake(String.format("SELECT `name`,`line`, COUNT(DISTINCT `workerID` ) AS `agreement` FROM `detected_smell` WHERE `microtaskID`='%s' AND `line`='%s' GROUP BY `name` ",microtaskID,i));
				
					while(rs.next()) {
						int agreement = rs.getInt("agreement");
						int worker_limit = (lines.getInt("limit_worker"))/2;						
						if(agreement > worker_limit) {
							OA.add(new MajorityVotingModel(microtaskID,rs.getString("name"),rs.getInt("line"),agreement));							
						}else {
							ResultSet majvot = connection.executeTake(String.format("SELECT * FROM majority_vote WHERE microtaskID LIKE '%s' AND smell_name LIKE '%s' AND line LIKE '%d' ", microtaskID,rs.getString("name"),rs.getInt("line")));
							
							if(!(majvot.next())) {
							//masukkan ke DB majority vote
								MajorityVotingModel votes = new MajorityVotingModel(microtaskID, rs.getString("name"),rs.getInt("line"),0);
								connection.executeStore(votes.saveVote());
							}else {
								MV.add(new MajorityVotingModel(microtaskID,majvot.getString("smell_name"),majvot.getInt("line"),majvot.getInt("votes")));
							}
						}
						
					}
				}
			}
			
			//disini utk tampilkan hasil QC
			request.setAttribute("OutAgree", OA);
			request.setAttribute("MajVot", MV);
			
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
