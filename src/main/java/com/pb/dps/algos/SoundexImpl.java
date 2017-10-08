package com.pb.dps.algos;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public class SoundexImpl implements PhoneticAlgorithm {

	public boolean getPhonetic(String str1, String str2)  throws Exception {
		Soundex s=new Soundex();
		int score = s.difference(str1, str2);
		if(score > 2) {
			return true;
		}
		return false;
	}
}
