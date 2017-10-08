package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public class SimilarityEnsembleAvg implements SimilarityEnsemble {

	@Override
	public Double getEnsembleOutput(List<EnsembleMethodInput> li) {
		Double sum_scores=0.0;
		Double avgScore=0.0;
		
		for(EnsembleMethodInput i:li){
			sum_scores=sum_scores+i.getAlgoScore();
		}
		
		avgScore=sum_scores/(li.size());
		
		return avgScore;
	}
	

}
