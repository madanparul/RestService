package com.pb.dps.algos;

import org.apache.commons.text.similarity.CosineDistance;

public class CosineDistanceImpl implements DistanceAlgorithm{
	public Double getDistance(String str1, String str2) {
		CharSequence cs1=str1;
		CharSequence cs2=str2;
		CosineDistance cd=new CosineDistance();
		Double cosineScore=cd.apply(cs1, cs2);
		return cosineScore;
	}
}
