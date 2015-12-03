package edu.mcs.model;

public class PredictorEntry {
	String index;
	Integer oldCounterVal;
	Integer newCounterVal;
	String prediction;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Integer getOldCounterVal() {
		return oldCounterVal;
	}
	public void setOldCounterVal(Integer oldCounterVal) {
		this.oldCounterVal = oldCounterVal;
	}
	public Integer getNewCounterVal() {
		return newCounterVal;
	}
	public void setNewCounterVal(Integer newCounterVal) {
		this.newCounterVal = newCounterVal;
	}
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	@Override
	public String toString() {
		return "\nLocalPredictorEntry [index=" + index + ", oldCounterVal=" + oldCounterVal + ", newCounterVal="
				+ newCounterVal + ", prediction=" + prediction + "]";
//		return "\n"+ prediction ;
	}
}
