package com.via.recommendationEngine;

import java.util.Scanner;

public class RecommendationEngine {

	public static void main(String[] args) throws Exception {

		MapUtility pMap = new MapUtility();
		Scanner input = new Scanner(System.in);

		try {
			System.out.println("Enter the product:");
			String key = input.nextLine();
			System.out.println("How many recommendations do you want?");
			int noOfRecos = input.nextInt();

			pMap.getRecommendation(key, noOfRecos);
			pMap.display(key, noOfRecos);
		} catch (NullPointerException e) {
			System.err.println("Error : No such product present ");
		}

		finally {
			input.close();
		}

	}

}
