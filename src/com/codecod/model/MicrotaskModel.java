package com.codecod.model;

import java.util.List;

import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;

public class MicrotaskModel {
	TaskModel task;
	SimpleName methodName;
	BlockStmt methodBody;
	String decompositionType;
	List<TypeOfSmell> smells;

	@Override
	public String toString() {
		return "MicrotaskModel [methodName=" + methodName + ", methodBody=" + methodBody + ", decompositionType="
				+ decompositionType + ", smells=" + smells + "]";
	}

	public MicrotaskModel(TaskModel task, SimpleName methodName, BlockStmt methodBody) {
		super();
		this.task = task;
		this.methodName = methodName;
		this.methodBody = methodBody;
	}

	public SimpleName getMethod_name() {
		return methodName;
	}

	public BlockStmt getMethod_body() {
		return methodBody;
	}

	public String getDecompositionType() {
		return decompositionType;
	}

	public void setDecompositionType(String decompositionType) {
		this.decompositionType = decompositionType;
	}

	public List<TypeOfSmell> getSmells() {
		return smells;
	}

	public void setSmells(List<TypeOfSmell> smells) {
		this.smells = smells;
	}

	public String insertMicrotask() {
		return String.format(
				"INSERT INTO `microtask` (`class_name`, `method_name`, `method_body`) VALUES ('%s','%s','%s')",task.getId(),this.methodName,this.methodBody);
	}
}
