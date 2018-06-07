package com.codecod.model;

public class MVmodel {
	String microtaskID;
	String smell;
	String acceptance;
	
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

	public String getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	
	public MVmodel(String microtaskID, String smell, String acceptance) {
		super();
		this.microtaskID = microtaskID;
		this.smell = smell;
		this.acceptance = acceptance;
	}
	
	// BELOM TERPAKAI
	
//	public String result_query() {
//		return String.format("SELECT `microtask`,`name`,`answered`,`jumWor` FROM "
//				+ "(SELECT `microtaskID` AS `microtask`,`name`,COUNT(DISTINCT `workerID`)AS `answered` FROM `detected_smell` "
//				+ "GROUP BY `microtaskID`,`name`) AS `firstPart` "
//				+ "INNER JOIN (SELECT `microtaskID`, COUNT(DISTINCT `workerID`) AS `jumWor` FROM `detected_smell`GROUP BY `microtaskID` ) AS `secondPart` "
//				+ "ON `firstPart`.`microtask` = `secondPart`.`microtaskID` ORDER BY `microtaskID` ");
//	}
//	
//	public String checkValidity(int counter, int total_worker) {
//		int halfValue = total_worker/2;
//		if(total_worker % 2 == 0) {
//			if(total_worker % counter < halfValue) {
//				this.acceptance = "accepted";
//			}else {
//				this.acceptance = "considered";
//			}
//		}else {
//			if(total_worker % counter <= halfValue) {
//				this.acceptance = "accepted";
//			}else {
//				this.acceptance = "considered";
//			}
//		}
//		return acceptance;
//	}

}
