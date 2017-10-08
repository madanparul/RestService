package com.pb.dps.ensemble;

import java.util.List;

import com.pb.dps.dto.EnsembleMethodInput;

public interface PhoneticsEnsemble {
    public boolean getEnsembleOutput(List<EnsembleMethodInput> li);
}
