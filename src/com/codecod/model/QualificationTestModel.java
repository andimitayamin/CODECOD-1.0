package com.codecod.model;

import java.io.File;

public class QualificationTestModel {
    String id;
    String description;
    File generatedTest;
    String answer;
    
    public void setId(String id) {
    	this.id = id;
    }
    public String getId() {
    	return id;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    public String getDescription() {
    	return description;
    }
    
    public void setGeneratedTest(File generatedTest) {
    	this.generatedTest = generatedTest;
    }
    public File getGeneratedTest() {
    	return generatedTest;
    }
}
