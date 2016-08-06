package datadidit.helpful.hints.csv.test.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimpleSample {
	private String firstName; 
	
	private String lastName; 
	
	private Date dob;
	
	public SimpleSample(){}
	
	public SimpleSample(String firstName, String lastName, Date dob){
		this.dob = dob;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
}
