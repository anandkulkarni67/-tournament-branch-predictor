package edu.mcs.predictors;

import java.util.List;

import edu.mcs.model.PredictorEntry;

public interface IPredictor {
	public List<PredictorEntry> generatePrediction();
}
