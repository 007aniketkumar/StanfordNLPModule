/**
 * 
 */
package com.ani.stanfordnlp;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * @author aniket
 *
 */
public class NERCRFClassfiierUsage {

	public static void main(String[] args) {
		try {
		System.out.println("ner crf classifier test");
	   // String serializedClassifier = "src/main/java/resources/english.muc.7class.distsim.crf.ser.gz";
	    String serializedClassifier = "src/main/java/resources/english.all.3class.distsim.crf.ser.gz";

	    AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

		System.out.println(" after ner crf classifier test");

	    String[] example = {"Good afternoon Nidhi, how are you today?",
        "I go to BANGALORE tomorrow." };
for (String str : example) {
	System.out.println("\n"  + classifier.classifyToString(str));
	System.out.println("---");

}

	}
		catch(Exception e) {
			
			System.out.println("exception caught" );
			e.printStackTrace();
	}
}
}