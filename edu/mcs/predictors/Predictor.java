package edu.mcs.predictors;

import static edu.mcs.util.Constants.MAX_SAT_CNT;
import static edu.mcs.util.Constants.MIN_SAT_CNT;

import java.util.LinkedList;
import java.util.List;

import edu.mcs.model.InputEntry;
import edu.mcs.model.PredictorEntry;

public class Predictor implements IPredictor{
	public List<InputEntry> inputEntries;
	public Integer successfulPredictionCounter;
	List<PredictorEntry> predictionEntries = new LinkedList<PredictorEntry>();
	
	public List<PredictorEntry> generatePrediction(){
		throw new IllegalAccessError("This method must be implemented by each sub-class !!");
	}
	
	protected void incrementSatCounter(PredictorEntry globalPredictorEntry) {
		if (globalPredictorEntry.getOldCounterVal() < MAX_SAT_CNT) {
			globalPredictorEntry.setNewCounterVal(globalPredictorEntry.getOldCounterVal() + 1);
		} else {
			globalPredictorEntry.setNewCounterVal(globalPredictorEntry.getOldCounterVal());
		}
	}
	
	protected void decrementSatCounter(PredictorEntry globalPredictorEntry) {
		if (globalPredictorEntry.getOldCounterVal() > MIN_SAT_CNT) {
			globalPredictorEntry.setNewCounterVal(globalPredictorEntry.getOldCounterVal() - 1);
		} else {
			globalPredictorEntry.setNewCounterVal(globalPredictorEntry.getOldCounterVal());
		}
	}
	
	public List<InputEntry> getInputEntries() {
		return inputEntries;
	}

	public void setInputEntries(List<InputEntry> inputEntries) {
		this.inputEntries = inputEntries;
	}

	public Integer getSuccessfulPredictionCounter() {
		return successfulPredictionCounter;
	}

	public void setSuccessfulPredictionCounter(Integer successfulPredictionCounter) {
		this.successfulPredictionCounter = successfulPredictionCounter;
	}

	public List<PredictorEntry> getPredictionEntries() {
		return predictionEntries;
	}

	public void setPredictionEntries(List<PredictorEntry> predictionEntries) {
		this.predictionEntries = predictionEntries;
	}
}
