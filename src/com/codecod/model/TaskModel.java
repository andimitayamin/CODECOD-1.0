package com.codecod.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.fileupload.FileItem;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TaskModel extends VoidVisitorAdapter<Void> {
	public static final String DIRECTORY = "D:\\Java EE Workspace\\Uploaded";
	String id;
	File file;
	int totalGeneratedMicrotask;

	public TaskModel(FileItem fileItem) throws Exception {
		super();
		this.file = new File(String.format("%s\\%s", DIRECTORY, fileItem.getName()));
		this.id = "0";
		fileItem.write(this.file);
	}
	
	public String getId() {
		return id;
	}

	public String insertTask() {
		return String.format(
				"INSERT INTO `task` (`ID`, `file_name`, `total_generated_microtask`) VALUES ('%s', '%s', '1')",
				id, file.getName());
	}

	public List<MicrotaskModel> getMicrotaskByMethod() throws FileNotFoundException {
		MethodVisitor visitor = new MethodVisitor(this);
		CompilationUnit cu = JavaParser.parse((file));
		cu.accept(visitor, null);
		return visitor.getMicrotask();

	}

	private static class MethodVisitor extends VoidVisitorAdapter<Void> {
		List<MicrotaskModel> microtasks = new ArrayList<>();
		TaskModel task;
		
		public MethodVisitor(TaskModel task) {
			super();
			this.task = task;
		}

		public void visit(MethodDeclaration n, Void arg) {
			SimpleName simpleName = n.getName();
			Optional<BlockStmt> opt = n.getBody();
			BlockStmt blockStmt = null;

			if (opt.isPresent()) {
				blockStmt = opt.get();
			}
			microtasks.add(new MicrotaskModel(task, simpleName, blockStmt));
			super.visit(n, arg);
		}

		public List<MicrotaskModel> getMicrotask() {
			return microtasks;
		}
	}
}
