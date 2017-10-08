package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public class SimilarityEnsembleWeightedAvg implements SimilarityEnsemble {

	@Override
	public Double getEnsembleOutput(List<EnsembleMethodInput> li) {
		Double sum_weightedScores=0.0;
		Double weighted_avgScore=0.0;
		
		for(EnsembleMethodInput i:li){
			sum_weightedScores=sum_weightedScores+(i.getAlgoScore()*i.getAlgoWeight());
		}
		
		weighted_avgScore=sum_weightedScores/(li.size());
		
		return weighted_avgScore;
	}
	

}
