package com.pb.dps.dto;

public class AlgoResponseDto {
	String algorithm=null;
	Double score=null;	

	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "AlgoResponseDto [algorithm=" + algorithm + ", score=" + score
				+ "]";
	}

}
