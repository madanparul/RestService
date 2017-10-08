package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public class DistanceEnsembleMin implements DistanceEnsemble {

	@Override
	public Double getEnsembleOutput(List<EnsembleMethodInput> li) {
		Double minScore=99.0;

		for(EnsembleMethodInput i:li){
			if(i.getAlgoScore() < minScore){
				minScore=i.getAlgoScore();
			}
		}
		return minScore;
	}		
}
