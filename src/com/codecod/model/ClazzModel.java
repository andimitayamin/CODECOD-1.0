package com.codecod.model;

import com.github.javaparser.ast.Node;

public class ClazzModel extends TaskModel{
	String clazzName;
	String clazzID;
	Node clazzBody;
	String path;
	

	public ClazzModel(String clazzID, Node clazzBody, String path) {
		super();
		this.clazzID = clazzID;
		this.path = path;
		this.clazzBody = clazzBody;
	}
	
	
	public ClazzModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public String getClazzID() {
		return clazzID;
	}

	public void setClazzID(String clazzID) {
		this.clazzID = clazzID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Node getClazzBody() {
		return clazzBody;
	}

	public void setClazzBody(Node clazzBody) {
		this.clazzBody = clazzBody;
	}

	@Override
	public String toString() {
		return "ClazzModel [clazzID=" + clazzID + ", path=" + path + ", clazzBody=" + clazzBody + "]";
	}
	
	public String insertClazz() {
		
		return String.format(
				"INSERT INTO `clazz_microtask`(`clazzID`, `clazz_body`, `path`) VALUES ('%s','%s','%s')",this.clazzID,this.clazzBody,this.path);	
	}
	
}
