package com.pb.dps.algos;

import org.apache.commons.codec.language.Metaphone;

public class MetaphoneAlgoImpl implements PhoneticAlgorithm {
	public boolean getPhonetic(String str1, String str2) throws Exception {
		Metaphone m=new Metaphone();
		return m.isMetaphoneEqual(str1, str2);
	}
}
