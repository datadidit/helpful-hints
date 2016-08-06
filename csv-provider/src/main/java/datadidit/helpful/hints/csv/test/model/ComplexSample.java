package datadidit.helpful.hints.csv.test.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import datadidit.helpful.hints.csv.provider.CSVTransformer;

public class ComplexSample implements CSVTransformer{
	private String studentId; 
	
	private Map<String, String> grades; 
	
	public ComplexSample(){}
	
	public ComplexSample(String studentId, Map<String, String> grades){
		this.studentId = studentId;
		this.grades = grades;
	}
	
	@Override
	public Map<?, ?> toMap() {
		Map<String, Object> myMap = new HashMap<>();
		myMap.put("studentId", studentId);
		myMap.putAll(grades);
		
		return myMap;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Map<String, String> getGrades() {
		return grades;
	}

	public void setGrades(Map<String, String> grades) {
		this.grades = grades;
	}

}
