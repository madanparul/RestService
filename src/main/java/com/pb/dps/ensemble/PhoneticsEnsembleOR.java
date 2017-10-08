package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public class PhoneticsEnsembleOR implements PhoneticsEnsemble {

	@Override
	public boolean getEnsembleOutput(List<EnsembleMethodInput> li) {
	for(EnsembleMethodInput i:li){
		if(i.getAlgoScore()==1){
			return true;
		}
	}
		return false;
	}
}
