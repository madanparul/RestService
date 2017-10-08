package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public interface SimilarityEnsemble {
    public Double getEnsembleOutput(List<EnsembleMethodInput> li);
    
}
