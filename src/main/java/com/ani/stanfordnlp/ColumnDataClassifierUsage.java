/**
 * 
 */
package com.ani.stanfordnlp;

import java.io.IOException;

import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.util.Pair;

/**
 * @author aniket
 *
 */
public class ColumnDataClassifierUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Demonstrating the usage of column data classifier");
		ColumnDataClassifier cdc = new ColumnDataClassifier("src/main/java/resources/cheese2007.prop"); 
		try {
			cdc.trainClassifier("src/main/java/resources/cheeseDisease.train");
			System.out.println("Training the column data classifier");
			
			System.out.println("Testing accuracy of ColumnDataClassifier");
		    Pair<Double, Double> performance = cdc.testClassifier("src/main/java/resources/cheeseDisease.test");
		    System.out.printf("Accuracy: %.3f; macro-F1: %.3f%n", performance.first(), performance.second());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
