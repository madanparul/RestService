package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public interface DistanceEnsemble {
    public Double getEnsembleOutput(List<EnsembleMethodInput> li);
}
