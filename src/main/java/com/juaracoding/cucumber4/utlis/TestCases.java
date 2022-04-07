package com.juaracoding.cucumber4.utlis;

public enum TestCases {

	T1("Testing Login Customer"),
	T2("Testing menu My Booking");
	
	private String testName;
	
	TestCases(String value){
		this.testName = value;
	}
	
	public String getTestName() {
		return testName;
	}
}
