package edu.mcs.model;

public class SelectorEntry extends PredictorEntry{

	String finalPrediction;
	
	public String getFinalPrediction() {
		return finalPrediction;
	}
	public void setFinalPrediction(String finalPrediction) {
		this.finalPrediction = finalPrediction;
	}
	
	@Override
	public String toString() {
		return "\nLocalPredictorEntry [index=" + index + ", oldCounterVal=" + oldCounterVal + ", newCounterVal="
				+ newCounterVal + ", prediction=" + prediction + ", finalPrediction="+ finalPrediction +"]";
	}
}
