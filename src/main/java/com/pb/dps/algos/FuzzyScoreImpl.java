package com.pb.dps.algos;

import java.util.Locale;

import org.apache.commons.text.similarity.FuzzyScore;

public class FuzzyScoreImpl implements SimilarityAlgorithm {

	public Double getSimilarity(String str1, String str2) {
		CharSequence cs1=str1;
		CharSequence cs2=str2;
		Locale bLocale = new Locale("en", "US");
		FuzzyScore fs=new FuzzyScore(bLocale);
		Integer fuzzy=fs.fuzzyScore(cs1, cs2);
		return Double.valueOf(fuzzy.doubleValue());
		
	}
	
}
