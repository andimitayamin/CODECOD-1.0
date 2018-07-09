package com.codecod.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TaskModel extends VoidVisitorAdapter<Void> {
	public String path;
	String description;
	String taskName;
	String reqId;
	int numOfMicrotask;
	int numOfTask;

	public String insertTask() {
		return String.format(
				"INSERT INTO `task`(`path`, `file_name`, `requester_id`, `description`,`num_of_worker`) VALUES ('%s', '%s','%s', '%s',4)",this.path,this.taskName,this.reqId,this.description);
	}
	

	public List<MicrotaskModel> getMicrotaskByMethod(List<String> PathFiles) throws FileNotFoundException {
		MethodVisitor visitor = new MethodVisitor();
		for(String temp : PathFiles) {
			visitor.setPath(temp.replace("\\", "\\\\"));
			CompilationUnit cu = JavaParser.parse(new File(temp));
			cu.accept(visitor, null);
		}
			return visitor.getMicrotask();
	}

	private static class MethodVisitor extends VoidVisitorAdapter<Void> {
		List<MicrotaskModel> microtasks = new ArrayList<>();
		
		public String path;

		public void setPath(String path) {
			this.path = path;
		}
		
		public void visit(MethodDeclaration n, Void arg) {
			//define method declaration
			String declaration = n.getDeclarationAsString();
			//define method name
			String simpleName = n.getNameAsString();			
			//define method body
			Optional<BlockStmt> opt = n.getBody();
			
			BlockStmt blockStmt = null;

			if (opt.isPresent()) {
				blockStmt = opt.get();
				
			}
			
			//random number for ID-ed microtask
			Random rand = new Random();
			int randomNum = rand.nextInt(500);
			
			microtasks.add(new MicrotaskModel(simpleName+randomNum, declaration,simpleName, blockStmt, this.path ));
			
			super.visit(n, arg);
		}

		public List<MicrotaskModel> getMicrotask() {
			return microtasks;
		}
	}
	
	public TaskModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TaskModel(int numOfMicrotask, int numOfTask, String taskName) {
		super();
		this.numOfMicrotask = numOfMicrotask;
		this.numOfTask = numOfTask;
		this.taskName = taskName;
	}


	public String getPath() {
		return path;
	}

	public void setTaskPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public int getNumOfMicrotask() {
		return numOfMicrotask;
	}


	public void setNumOfMicrotask(int numOfMicrotask) {
		this.numOfMicrotask = numOfMicrotask;
	}
	

	public int getNumOfTask() {
		return numOfTask;
	}


	public void setNumOfTask(int numOfTask) {
		this.numOfTask = numOfTask;
	}


	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	public String getReqId() {
		return reqId;
	}


}
