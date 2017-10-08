package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public class DistanceEnsembleMax implements DistanceEnsemble {
	@Override
	public Double getEnsembleOutput(List<EnsembleMethodInput> li) {
		Double maxScore=-99.0;

		for(EnsembleMethodInput i:li){
			if(i.getAlgoScore() > maxScore){
				maxScore=i.getAlgoScore();
			}
		}
		return maxScore;
	}		
}
