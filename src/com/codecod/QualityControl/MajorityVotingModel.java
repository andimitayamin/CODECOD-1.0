package com.codecod.QualityControl;

public class MajorityVotingModel {
	String microtaskID;
	String smell;
	int line;
	int voter;
	
	
	public MajorityVotingModel(String microtaskID, String smell, int line, int voter) {
		super();
		this.microtaskID = microtaskID;
		this.smell = smell;
		this.line = line;
		this.voter = voter;
	}
	
	public String saveVote() {
		return String.format("INSERT INTO `majority_vote`(`microtaskID`, `smell_name`, `line`, `votes`) VALUES ('%s','%s','%d','%d')",this.microtaskID, this.smell, this.line, this.voter);
	}
	
	public String getMicrotaskID() {
		return microtaskID;
	}
	public void setMicrotaskID(String microtaskID) {
		this.microtaskID = microtaskID;
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
	public int getVoter() {
		return voter;
	}
	public void setVoter(int voter) {
		this.voter = voter;
	}
	
	
	
}
