package edu.mcs.predictors;

import static edu.mcs.util.Constants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mcs.model.InputEntry;
import edu.mcs.model.PredictorEntry;

public class LocalPredictor extends Predictor {

	public List<PredictorEntry> generatePrediction() {
		successfulPredictionCounter = 0;
		PredictorEntry localPredictorEntry;
		Map<String, Integer> brAddrAgainstrecentPred = new HashMap<String, Integer>();
		for (InputEntry inputEntry : inputEntries) {
			localPredictorEntry = new PredictorEntry();
			localPredictorEntry.setIndex(inputEntry.getAddOfBranch());
			if (brAddrAgainstrecentPred.get(inputEntry.getAddOfBranch()) == null) {
				localPredictorEntry.setOldCounterVal(DEF_SAT_CNT);
				localPredictorEntry.setPrediction(BR_NOT_TAKEN);
				if (inputEntry.getActPrediction().equals(BR_NOT_TAKEN)) {
					successfulPredictionCounter++;
					localPredictorEntry.setNewCounterVal(MIN_SAT_CNT);
				} else {
					localPredictorEntry.setNewCounterVal(1);
				}
				brAddrAgainstrecentPred.put(inputEntry.getAddOfBranch(), localPredictorEntry.getNewCounterVal());
			} else {
				localPredictorEntry.setOldCounterVal(brAddrAgainstrecentPred.get(inputEntry.getAddOfBranch()));
				if (localPredictorEntry.getOldCounterVal() < 2) {
					localPredictorEntry.setPrediction(BR_NOT_TAKEN);
					if (inputEntry.getActPrediction().equals(BR_NOT_TAKEN)) {
						successfulPredictionCounter++;
						decrementSatCounter(localPredictorEntry);
					} else {
						incrementSatCounter(localPredictorEntry);
					}
				} else {
					localPredictorEntry.setPrediction(BR_TAKEN);
					if (inputEntry.getActPrediction().equals(BR_TAKEN)) {
						successfulPredictionCounter++;
						incrementSatCounter(localPredictorEntry);
					} else {
						decrementSatCounter(localPredictorEntry);
					}
				}
				brAddrAgainstrecentPred.put(inputEntry.getAddOfBranch(), localPredictorEntry.getNewCounterVal());
			}
			predictionEntries.add(localPredictorEntry);
		}
		return predictionEntries;
	}
}