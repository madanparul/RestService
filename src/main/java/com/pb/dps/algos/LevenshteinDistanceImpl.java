package com.pb.dps.algos;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class LevenshteinDistanceImpl implements DistanceAlgorithm{
	public Double getDistance(String str1, String str2) {
		CharSequence cs1=str1;
		CharSequence cs2=str2;
		LevenshteinDistance ld=new LevenshteinDistance();
		ld.apply(cs1,cs2);
		return Double.valueOf(ld.apply(cs1,cs2).doubleValue());
	} 

}
