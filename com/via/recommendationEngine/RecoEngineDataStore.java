package com.via.recommendationEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RecoEngineDataStore {

	public Map<String, Map<String, Integer>> recommendationProductMap = new HashMap<String, Map<String, Integer>>();

	public RecoEngineDataStore() {

	}

	// Adds the value associated with product1 and product2 to the hashmap
	public void put(String product1, String product2, Integer count) {
		Map<String, Integer> map = recommendationProductMap.get(product1);

		if (map == null) {
			map = new HashMap<String, Integer>();
			recommendationProductMap.put(product1, map);
		}

		map.put(product2, count);
	}

	// Fetches the value associated with product1 and product2
	public Integer get(String product1, String product2) {
		if (recommendationProductMap.containsKey(product1)) {
			return recommendationProductMap.get(product1).get(product2);
		}
		return null;
	}

	public Map<String, Integer> get(String key) {
		return recommendationProductMap.get(key);
	}

	// Increments the count associated with product1 and product2
	public int increment(String product1, String product2) {
		Integer temp;
		temp = this.get(product1, product2);
		this.put(product1, product2, temp + 1);
		return temp + 1;
	}

	public void displayRecommendation(Map<String, Integer> secondaryKeyMap,String key1, Integer n) {
		int i = 0;
		for (Entry<String, Integer> secondEntry : secondaryKeyMap.entrySet()) {
			if (i < n) {
				++i;
				System.out.println("" + key1 + " " + secondEntry.getKey()
						+ " Value : " + secondEntry.getValue());
			} else {
				break;
			}

		}
	}

}
