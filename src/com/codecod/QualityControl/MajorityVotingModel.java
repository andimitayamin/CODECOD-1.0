package com.codecod.QualityControl;

public class MajorityVotingModel extends OutputAgreementModel{
	String microtaskID;
	String microtaskName;
	String smell;
	int line;
	int vote_up;
	int vote_down;
	String voter;
	
	public int getVote_up() {
		return vote_up;
	}

	public void setVote_up(int vote_up) {
		this.vote_up = vote_up;
	}

	public int getVote_down() {
		return vote_down;
	}

	public void setVote_down(int vote_down) {
		this.vote_down = vote_down;
	}	
	
	public MajorityVotingModel(String microtaskID, String smell, int line, String voter) {
		super();
		this.microtaskID = microtaskID;
		this.smell = smell;
		this.line = line;
		this.voter = voter;
	}
	
	public MajorityVotingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String saveVote() {
		return String.format("INSERT INTO `majority_vote`(`microtaskID`, `smell_name`, `line`, `voter_id`) VALUES ('%s','%s','%d','%d')",this.microtaskID, this.smell, this.line, this.voter);
	}
	
	public String getMicrotaskID() {
		return microtaskID;
	}
	public void setMicrotaskID(String microtaskID) {
		this.microtaskID = microtaskID;
	}
	
	public String getMicrotaskName() {
		return microtaskName;
	}

	public void setMicrotaskName(String microtaskName) {
		this.microtaskName = microtaskName;
	}

	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}
	
	
	
}
