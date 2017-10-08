package com.pb.dps.algos;

import org.apache.commons.text.similarity.JaroWinklerDistance;

public class JaroWinklerDistanceImpl implements DistanceAlgorithm {


	public Double getDistance(String str1, String str2) {
		CharSequence cs1=str1;
		CharSequence cs2=str2;
		JaroWinklerDistance jwd=new JaroWinklerDistance();
		return jwd.apply(cs1,cs2);
	}

}
