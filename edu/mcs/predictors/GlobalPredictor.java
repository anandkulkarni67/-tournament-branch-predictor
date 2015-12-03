package edu.mcs.predictors;

import static edu.mcs.util.Constants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mcs.model.PredictorEntry;

public class GlobalPredictor extends Predictor {

	public List<PredictorEntry> generatePrediction() {
		successfulPredictionCounter = 0;
		PredictorEntry globalPredictorEntry;
		Map<String, Integer> brAddrAgainstrecentPred = new HashMap<String, Integer>();
		// Processing first entry manually since it has a default index.
		globalPredictorEntry = new PredictorEntry();
		globalPredictorEntry.setIndex(GLOB_PREDN_DEF_INDEX);
		globalPredictorEntry.setOldCounterVal(DEF_SAT_CNT);
		globalPredictorEntry.setPrediction(BR_NOT_TAKEN);
		if (inputEntries.get(0).equals(BR_TAKEN)) {
			globalPredictorEntry.setNewCounterVal(1);
		} else {
			successfulPredictionCounter++;
			globalPredictorEntry.setNewCounterVal(MIN_SAT_CNT);
		}
		predictionEntries.add(globalPredictorEntry);
		brAddrAgainstrecentPred.put(globalPredictorEntry.getIndex(), globalPredictorEntry.getNewCounterVal());
		for (int counter = 1; counter < inputEntries.size(); counter++) {
			globalPredictorEntry = new PredictorEntry();
			String prevIndex = predictionEntries.get(counter - 1).getIndex();
			globalPredictorEntry
					.setIndex(generateNextIndex(prevIndex, inputEntries.get(counter - 1).getActPrediction()));
			if (brAddrAgainstrecentPred.get(globalPredictorEntry.getIndex()) == null) {
				globalPredictorEntry.setOldCounterVal(DEF_SAT_CNT);
				globalPredictorEntry.setPrediction(BR_NOT_TAKEN);
				if (inputEntries.get(counter).getActPrediction().equals(BR_NOT_TAKEN)) {
					successfulPredictionCounter++;
					globalPredictorEntry.setNewCounterVal(MIN_SAT_CNT);
				} else {
					globalPredictorEntry.setNewCounterVal(1);
				}
				brAddrAgainstrecentPred.put(globalPredictorEntry.getIndex(), globalPredictorEntry.getNewCounterVal());
			} else {
				globalPredictorEntry.setOldCounterVal(brAddrAgainstrecentPred.get(globalPredictorEntry.getIndex()));
				// If saturating counter has value 0 or 1 then set prediction to "BRANCH NOT TAKEN".
				if (globalPredictorEntry.getOldCounterVal() < 2) {
					globalPredictorEntry.setPrediction(BR_NOT_TAKEN);
					// Check if current prediction matches with actual prediction given in the input entry.
					if (inputEntries.get(counter).getActPrediction().equals(globalPredictorEntry.getPrediction())) {
						successfulPredictionCounter++;
						decrementSatCounter(globalPredictorEntry);
					} else {
						incrementSatCounter(globalPredictorEntry);
					}
				} else {
					// If saturating counter has value 2 or 3 then set prediction to "BRANCH TAKEN".
					globalPredictorEntry.setPrediction(BR_TAKEN);
					if (inputEntries.get(counter).getActPrediction().equals(BR_TAKEN)) {
						successfulPredictionCounter++;
						incrementSatCounter(globalPredictorEntry);
					} else {
						decrementSatCounter(globalPredictorEntry);
					}
				}
				brAddrAgainstrecentPred.put(globalPredictorEntry.getIndex(), globalPredictorEntry.getNewCounterVal());
			}
			predictionEntries.add(globalPredictorEntry);
		}
		return predictionEntries;
	}

	private String generateNextIndex(String prevIndex, String actPrediction) {
		return prevIndex.substring(1, prevIndex.length()) + actPrediction;
	}
}
