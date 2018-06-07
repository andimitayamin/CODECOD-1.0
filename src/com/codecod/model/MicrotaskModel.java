package com.codecod.model;

import java.util.List;

import com.github.javaparser.ast.stmt.BlockStmt;

public class MicrotaskModel {
	String microtaskID;
	String declaration;
	String pathFile;
	String methodName;
	BlockStmt methodBody;
	String decompositionType;
	List<TypeOfSmell> smells;
	

	@Override
	public String toString() {
		return "MicrotaskModel [declaration=" + declaration +", methodName=" + methodName + ", methodBody=" + methodBody + ", decompositionType="
				+ decompositionType + ", smells=" + smells + "]";
	}

	public MicrotaskModel(String microtaskID, String declaration, String methodName, BlockStmt methodBody, String path) {
		super();
		this.microtaskID = microtaskID;
		this.declaration = declaration;
		this.methodName = methodName;
		this.methodBody = methodBody;
		this.pathFile = path;
	}
	
	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public BlockStmt getMethodBody() {
		return methodBody;
	}
	
	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setMethodBody(BlockStmt method_body) {
		this.methodBody = method_body;
	}

	public String getDecompositionType() {
		return decompositionType;
	}

	public void setDecompositionType(String decompositionType) {
		this.decompositionType = decompositionType;
	}

	
	public String insertMicrotask() {
		return String.format(
				"INSERT INTO `microtask`(`method_id`, `declaration`, `method_name`, `method_body`, `path`) VALUES ('%s','%s','%s','%s','%s')",this.microtaskID,this.declaration,this.methodName,this.methodBody, this.pathFile);
	}

	public String getMicrotaskID() {
		return microtaskID;
	}

	public void setMicrotaskID(String microtaskID) {
		this.microtaskID = microtaskID;
	}
}
