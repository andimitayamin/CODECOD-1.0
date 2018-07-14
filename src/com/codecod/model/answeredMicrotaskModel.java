package com.codecod.model;

public class answeredMicrotaskModel {
	String microtaskID;
	String name;
	String line;
	String workerID;
	String answerID;
	String microtaskName;
	String suggestedRefactoring;

	public answeredMicrotaskModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getSuggestedRefactoring() {
		return suggestedRefactoring;
	}

	public void setSuggestedRefactoring(String suggestedRefactoring) {
		this.suggestedRefactoring = suggestedRefactoring;
	}

	public answeredMicrotaskModel(String answerID,String microtaskID, String name, String line) {
		super();
		this.answerID = answerID;
		this.microtaskID = microtaskID;
		this.name = name;
		this.line = line;
	}
	
	public String getMicrotaskName() {
		return microtaskName;
	}

	public void setMicrotaskName(String microtaskName) {
		this.microtaskName = microtaskName;
	}
	
	public String getAnswerID() {
		return answerID;
	}

	public void setAnswerID(String answerID) {
		this.answerID = answerID;
	}
	
	public String getMicrotaskID() {
		return microtaskID;
	}


	public void setMicrotaskID(String microtaskID) {
		this.microtaskID = microtaskID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public String getWorkerID() {
		return workerID;
	}


	public void setWorkerID(String workerID) {
		this.workerID = workerID; 
	}

	
	public String save_answer() {
		return String.format(
				"INSERT INTO `detected_smell`(`answerID`, `microtaskID`, `smell_name`, `line`) VALUES ('%s','%s','%s','%s')",this.answerID, this.microtaskID,this.name,this.line);
	}
	
	public String addWorkerHistory() {
		return String.format("INSERT INTO `worker_history`(`workerID`, `answerID`, `suggested_refactoring`) VALUES ('%s','%s','%s')", this.workerID,this.answerID,this.suggestedRefactoring);
	}
}
