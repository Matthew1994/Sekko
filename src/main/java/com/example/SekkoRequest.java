package com.example;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SekkoRequest {
	
	@Size(min=2, max=30)
	private String name;
	
	@NotNull
	@Min(1)
	@Max(3)
	private Integer number;
	
	private String[] requestlist = {"a", "b", "c"};
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getRequest() {
		return requestlist[number - 1];
	}
	
	public void setNumber(Integer n) {
		number = n;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public String toString() {
        return "SekkoRequest(Name: " + this.name + ", SekkoRequest: " + this.getRequest() + ")";
    }
}