package com.codecod.model;

public class answeredMicrotaskModel {
	String microtaskID;
	String name;
	String line;
	String workerID;
	
	public answeredMicrotaskModel(String microtaskID, String name, String line, String workerID) {
		super();
		this.microtaskID = microtaskID;
		this.name = name;
		this.line = line;
		this.workerID = workerID;
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
				"INSERT INTO `detected_smell`(`microtaskID`, `name`, `line`, `workerID`) VALUES ('%s','%s','%s','123')", this.microtaskID,this.name,this.line);
	}
}
