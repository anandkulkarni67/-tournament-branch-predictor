package edu.mcs.fileIO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import edu.mcs.model.InputEntry;
import edu.mcs.model.PredictorEntry;
import edu.mcs.model.SelectorEntry;

public class OutputWriter {
	private String fileName;
	private Iterator<PredictorEntry> localPredResultItr = null;
	private Iterator<PredictorEntry> globalPredResultItr = null;
	private Iterator<InputEntry> ipItr = null;
	private Iterator<PredictorEntry> tPredResultItr = null;

	public OutputWriter(String fileName) {
		this.fileName = fileName;
	}

	public void writeOutput() {
		StringBuffer stringBuffer = new StringBuffer("");
		while (localPredResultItr.hasNext() && globalPredResultItr.hasNext() && ipItr.hasNext()
				&& tPredResultItr.hasNext()) {
			InputEntry ipEntry = ipItr.next();
			SelectorEntry sEntry = (SelectorEntry) tPredResultItr.next();
			stringBuffer.append(ipEntry.getAddOfBranch() + localPredResultItr.next().getPrediction()
					+ globalPredResultItr.next().getPrediction() + sEntry.getPrediction() + sEntry.getFinalPrediction()
					+ ipEntry.getActPrediction() + "\n");
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(this.fileName));
			out.print(stringBuffer);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterator<PredictorEntry> getLocalPredResultItr() {
		return localPredResultItr;
	}

	public void setLocalPredResultItr(Iterator<PredictorEntry> localPredResultItr) {
		this.localPredResultItr = localPredResultItr;
	}

	public Iterator<PredictorEntry> getGlobalPredResultItr() {
		return globalPredResultItr;
	}

	public void setGlobalPredResultItr(Iterator<PredictorEntry> globalPredResultItr) {
		this.globalPredResultItr = globalPredResultItr;
	}

	public Iterator<InputEntry> getIpItr() {
		return ipItr;
	}

	public void setIpItr(Iterator<InputEntry> ipItr) {
		this.ipItr = ipItr;
	}

	public Iterator<PredictorEntry> gettPredResultItr() {
		return tPredResultItr;
	}

	public void settPredResultItr(Iterator<PredictorEntry> tPredResultItr) {
		this.tPredResultItr = tPredResultItr;
	}
}
