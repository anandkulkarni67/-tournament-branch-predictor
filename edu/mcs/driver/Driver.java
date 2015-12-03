package edu.mcs.driver;

import java.util.List;

import edu.mcs.fileIO.InputLoader;
import edu.mcs.fileIO.OutputWriter;
import edu.mcs.model.InputEntry;
import edu.mcs.model.PredictorEntry;
import edu.mcs.predictors.GlobalPredictor;
import edu.mcs.predictors.LocalPredictor;
import edu.mcs.predictors.Predictor;
import edu.mcs.predictors.TournamentPredictor;

public class Driver {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("Please enter input filename/filepath.");
		} else if (args.length > 2) {
			throw new IllegalArgumentException("Please enter valid arguments...");
		}
		InputLoader inputLoader = new InputLoader(args[0]);
		List<InputEntry> inputEntries = inputLoader.load();

		Predictor localPredictor = new LocalPredictor();
		localPredictor.setInputEntries(inputEntries);
		List<PredictorEntry> localPredictionResult = localPredictor.generatePrediction();

		Predictor globalPredictor = new GlobalPredictor();
		globalPredictor.setInputEntries(inputEntries);
		List<PredictorEntry> globalPredictionResult = globalPredictor.generatePrediction();

		TournamentPredictor tournamentPredictor = new TournamentPredictor(localPredictor.generatePrediction(),
				globalPredictor.generatePrediction());
		tournamentPredictor.setInputEntries(inputEntries);
		List<PredictorEntry> tournamentPredictionResult = tournamentPredictor.generatePrediction();

		OutputWriter outputWriter = new OutputWriter(args[1]);
		outputWriter.setIpItr(inputEntries.iterator());
		outputWriter.setLocalPredResultItr(localPredictionResult.iterator());
		outputWriter.setGlobalPredResultItr(globalPredictionResult.iterator());
		outputWriter.settPredResultItr(tournamentPredictionResult.iterator());
		outputWriter.writeOutput();

		System.out.println("Number of correct predictions using only Local Predictor - "
				+ localPredictor.getSuccessfulPredictionCounter());
		System.out.println("Number of correct predictions using only Global Predictor - "
				+ globalPredictor.getSuccessfulPredictionCounter());
		System.out.println("Number of correct Predictions using Tournament Predictor - "
				+ tournamentPredictor.getSuccessfulPredictionCounter());
		System.out.println("Tournament Predictor algorithm completed successfully. Please see the output file to verify generated result.");
	}
}
