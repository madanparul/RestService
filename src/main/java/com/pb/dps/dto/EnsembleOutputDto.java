package com.pb.dps.dto;

import java.util.ArrayList;
import java.util.List;

public class EnsembleOutputDto {
	String inputString1= null;
	String inputString2= null;
	Double matchScore= null;
	List<AlgoResponseDto> scoreList = new ArrayList<AlgoResponseDto>();
	public String getInputString1() {
		return inputString1;
	}
	public void setInputString1(String inputString1) {
		this.inputString1 = inputString1;
	}
	public String getInputString2() {
		return inputString2;
	}
	public void setInputString2(String inputString2) {
		this.inputString2 = inputString2;
	}
	public Double getMatchScore() {
		return matchScore;
	}
	public void setMatchScore(Double matchScore) {
		this.matchScore = matchScore;
	}
	public List<AlgoResponseDto> getScoreList() {
		return scoreList;
	}
	
	@Override
	public String toString() {
		return "EnsembleOutputDto [inputString1=" + inputString1
				+ ", inputString2=" + inputString2 + ", matchScore="
				+ matchScore + ", scoreList=" + scoreList + "]";
	}

}
