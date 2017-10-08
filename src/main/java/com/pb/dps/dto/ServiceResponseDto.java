package com.pb.dps.dto;

public class ServiceResponseDto {
	String name= null;
	String riskLevel= null;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ServiceResponseDto [name=" + name + ", riskLevel=" + riskLevel
				+ "]";
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	
}
