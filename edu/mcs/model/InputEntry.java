package edu.mcs.model;

public class InputEntry {
	public String addOfBranch;
	public String actPrediction;
	public String nextInstAdd;

	public InputEntry() {

	}

	public InputEntry(String addOfBranch, String actPrediction, String nextInstAdd) {
		super();
		this.addOfBranch = addOfBranch;
		this.actPrediction = actPrediction;
		this.nextInstAdd = nextInstAdd;
	}

	public String getAddOfBranch() {
		return addOfBranch;
	}

	public void setAddOfBranch(String addOfBranch) {
		this.addOfBranch = addOfBranch;
	}

	public String getActPrediction() {
		return actPrediction;
	}

	public void setActPrediction(String actPrediction) {
		this.actPrediction = actPrediction;
	}

	public String getNextInstAdd() {
		return nextInstAdd;
	}

	public void setNextInstAdd(String nextInstAdd) {
		this.nextInstAdd = nextInstAdd;
	}

	@Override
	public String toString() {
		return addOfBranch + "-" + actPrediction + "-" + nextInstAdd;
	}
}
