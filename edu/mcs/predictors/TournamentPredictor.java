package edu.mcs.predictors;

import static edu.mcs.util.Constants.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.mcs.model.InputEntry;
import edu.mcs.model.PredictorEntry;
import edu.mcs.model.SelectorEntry;

public class TournamentPredictor extends Predictor {

	List<PredictorEntry> localPredictorEntries;
	List<PredictorEntry> globalPredictorEntries;

	public TournamentPredictor(List<PredictorEntry> localPredictorEntries,
			List<PredictorEntry> globalPredictorEntries) {
		super();
		this.localPredictorEntries = localPredictorEntries;
		this.globalPredictorEntries = globalPredictorEntries;
	}

	public List<PredictorEntry> generatePrediction() {
		successfulPredictionCounter = 0;
		SelectorEntry selectorEntry;
		Map<String, Integer> brAddrAgainstrecentPred = new HashMap<String, Integer>();
		Iterator<PredictorEntry> lItr = localPredictorEntries.iterator();
		Iterator<PredictorEntry> gItr = globalPredictorEntries.iterator();
		Iterator<InputEntry> ipItr = inputEntries.iterator();
		while (lItr.hasNext() && gItr.hasNext() && ipItr.hasNext()) {
			selectorEntry = new SelectorEntry();
			PredictorEntry locPredE = lItr.next();
			PredictorEntry gloPredE = gItr.next();
			InputEntry ipE = ipItr.next();
			selectorEntry.setIndex(ipE.getAddOfBranch());
			if (brAddrAgainstrecentPred.get(selectorEntry.getIndex()) == null) {
				selectorEntry.setOldCounterVal(DEF_SAT_CNT);
				selectorEntry.setPrediction(LOCAL);
				selectorEntry.setFinalPrediction(locPredE.getPrediction());
				if (locPredE.getPrediction().equals(gloPredE.getPrediction())) {
					if (locPredE.getPrediction().equals(ipE.getActPrediction())) {
						successfulPredictionCounter++;
					}
					selectorEntry.setNewCounterVal(MIN_SAT_CNT);
				} else if (locPredE.getPrediction().equals(ipE.getActPrediction())) {
					successfulPredictionCounter++;
					selectorEntry.setNewCounterVal(MIN_SAT_CNT);
				} else {
					selectorEntry.setNewCounterVal(1);
				}
				brAddrAgainstrecentPred.put(selectorEntry.getIndex(), selectorEntry.getNewCounterVal());
			} else {
				selectorEntry.setOldCounterVal(brAddrAgainstrecentPred.get(selectorEntry.getIndex()));
				if (selectorEntry.getOldCounterVal() < 2) {
					selectorEntry.setPrediction(LOCAL);
					selectorEntry.setFinalPrediction(locPredE.getPrediction());
				} else {
					selectorEntry.setPrediction(GLOBAL);
					selectorEntry.setFinalPrediction(gloPredE.getPrediction());
				}
				if (locPredE.getPrediction().equals(gloPredE.getPrediction())) {
					if (locPredE.getPrediction().equals(ipE.getActPrediction())) {
						successfulPredictionCounter++;
					}
					selectorEntry.setNewCounterVal(selectorEntry.getOldCounterVal());
				} else if (locPredE.getPrediction().equals(ipE.getActPrediction())) {
					if (selectorEntry.getPrediction().equals(LOCAL)) {
						successfulPredictionCounter++;
					}
					if (selectorEntry.getOldCounterVal() > MIN_SAT_CNT) {
						selectorEntry.setNewCounterVal(selectorEntry.getOldCounterVal() - 1);
					} else {
						selectorEntry.setNewCounterVal(selectorEntry.getOldCounterVal());
					}
				} else {
					if (selectorEntry.getPrediction().equals(GLOBAL)) {
						successfulPredictionCounter++;
					}
					if (selectorEntry.getOldCounterVal() < MAX_SAT_CNT) {
						selectorEntry.setNewCounterVal(selectorEntry.getOldCounterVal() + 1);
					} else {
						selectorEntry.setNewCounterVal(selectorEntry.getOldCounterVal());
					}
				}
				brAddrAgainstrecentPred.put(selectorEntry.getIndex(), selectorEntry.getNewCounterVal());
			}
			predictionEntries.add(selectorEntry);
		}
		return predictionEntries;
	}

	public List<PredictorEntry> getLocalPredictorEntries() {
		return localPredictorEntries;
	}

	public void setLocalPredictorEntries(List<PredictorEntry> localPredictorEntries) {
		this.localPredictorEntries = localPredictorEntries;
	}

	public List<PredictorEntry> getGlobalPredictorEntries() {
		return globalPredictorEntries;
	}

	public void setGlobalPredictorEntries(List<PredictorEntry> globalPredictorEntries) {
		this.globalPredictorEntries = globalPredictorEntries;
	}
}