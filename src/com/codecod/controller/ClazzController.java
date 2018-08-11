package com.codecod.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.codecod.model.ClazzModel;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClazzController {
	
	public List<ClazzModel> getMicrotaskByClass(List<String> PathFiles) throws FileNotFoundException {
		ClassVisitor visitor = new ClassVisitor();
		for(String temp : PathFiles) {
			visitor.setPath(temp.replace("\\", "\\\\"));
			CompilationUnit cu = JavaParser.parse(new File(temp));
			cu.accept(visitor, null);
		}
			return visitor.getClazz();
	}

	private static class ClassVisitor extends VoidVisitorAdapter<Void> {
		List<ClazzModel> clazz = new ArrayList<>();		
		public String path;

		public void setPath(String path) {
			this.path = path;
		}
		
		public void visit(ClassOrInterfaceDeclaration n, Void arg) {
			
			Optional<Node> opt = n.getParentNode();
			String clazzName = n.getNameAsString();
			Node blockStmt = null;

			if (opt.isPresent()) {
				blockStmt = opt.get();
				
			}
			
			//random number for ID-ed microtask
			Random rand = new Random();
			int randomNum = rand.nextInt(500);
			
			
			clazz.add(new ClazzModel("clazz-"+clazzName+randomNum, blockStmt, this.path ));
			
			super.visit(n, arg);
		}

		public List<ClazzModel> getClazz() {
			return clazz;
		}
	}
}
