package com.hemebiotech.analytics;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyticsCounter {
	
	String symptomsFilePath = "C:\\Users\\Ocean\\hemeBiotechOC\\Project_DA_Java_EN_Come_to_the_Rescue_of_a_Java_Application\\Project02Eclipse\\symptoms.txt";
	SymptomsReader reader = new SymptomsReader(symptomsFilePath);
	
	/**
	 * used to group symptoms by their occurrences
	 * 
	 * @return symptomsOccurences
	 */
	public Map<String, Long> symptomsOccurences() {
		return reader.getSymptoms().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	/**
	 * Convert Map to TreeMap to sort symptomsOccurences alphabetically
	 * 
	 * @return sortedSymptomsOccurences
	 */
	public Map<String, Long> sortSymptomsOccurrences() {
		Map<String, Long> sortedSymptomsOccurences = new TreeMap<String, Long>();
		sortedSymptomsOccurences.putAll(symptomsOccurences());
		return sortedSymptomsOccurences;
	}
	
	/**
	 * Writes symptoms' Occurrences into a file named "result.out"
	 * 
	 * @param sortedSymptomsOccurences
	 * @throws IOException
	 */
	public void writeSymptomsOccurrencesFile(Map<String, Long> sortedSymptomsOccurences) throws IOException {
		String result = "Symptoms Occurrences: \n";
		for (Entry<String, Long> entry : sortedSymptomsOccurences.entrySet()) {
			result += entry.getKey() + "=" + entry.getValue() + ";\n";
		} // Used a for loop to display Map without curly brackets
		BufferedWriter bw = new BufferedWriter(new FileWriter("results.out"));
		bw.write(result);
		bw.close();
	}
	
	
	public static void main(String args[]) throws Exception {
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		analyticsCounter.writeSymptomsOccurrencesFile(analyticsCounter.sortSymptomsOccurrences());
	}
}