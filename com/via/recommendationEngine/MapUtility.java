package com.via.recommendationEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapUtility {

	private RecoEngineDataStore productMap = new RecoEngineDataStore();
	Map<String, Integer> recoProductMap = new TreeMap<String, Integer>();

	MapUtility() {
		try {
			productMap = this.intialise();

		} catch (IOException e) {
			System.err.println("Error : Unable to access file " + e);
		}

	}

	public Map<String, Integer> getRecommendation(String product, int count) {
		Map<String, Integer> tempMap = productMap.get(product);

		ValueComparator bvc = new ValueComparator(tempMap);
		recoProductMap = new TreeMap<String, Integer>(bvc);
		recoProductMap.putAll(tempMap);
		return recoProductMap;
	}

	public RecoEngineDataStore intialise() throws IOException {

		BufferedReader csvFile = null;
		try {
			csvFile = new BufferedReader(new FileReader(
					"resource/transactionList.csv"));
			String dataRow;

			while ((dataRow = csvFile.readLine()) != null) {
				String[] dataArray = dataRow.split(",");
				int len = dataArray.length;

				for (int i = 0; i < len - 1; i++) {
					for (int j = i + 1; j < len; j++) {
						String product1 = dataArray[i].trim();
						String product2 = dataArray[j].trim();
						insertToMap(product1, product2);
						insertToMap(product2, product1);
					}
				}
			}

		} finally {
			if (csvFile != null) {
				csvFile.close();
			}
		}
		return productMap;

	}

	public void display(String key, Integer num) {
		productMap.displayRecommendation(recoProductMap, key, num);
	}

	public RecoEngineDataStore insertToMap(String p1, String p2) {

		Integer val = productMap.get(p1, p2);

		productMap.put(p1, p2, (val == null) ? 1 : val + 1);

		return productMap;
	}

}

class ValueComparator implements Comparator<String> {

	Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} 
	}
}
