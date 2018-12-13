/**
 * 
 */
package com.ani.stanfordnlp;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author aniket
 *
 */
public class LuceneBasedSpellChecker {

	/**
	 * @param args
	 * 
	 * 
	 * Basic spell checker Test.
	 * 
	 * This is based on Lucene based spell checker library.
	 * 
	 * Note : That the lucene spell checker library is present in Lucene suggest , so gradle has dependency of lucene suggest
	 * 
	 * 
	 * 
	 * 
	 * /Users/aniket/Documents/workspace/luceneSetup/TestDictionary is present under the resources folder , only for reference purpose.
	 * 
	 * Always keep the above file upto date with the TestDictionary under luceneSetup , to avoid any confusion.
	 *
	 */
	
	public static void main(String[] args) {

		
		Path dir =  Paths.get("/Users/aniket/Documents/workspace/luceneSetup/luceneIndexTestOnly");
        try {
			Directory directory = FSDirectory.open(dir);
			
			SpellChecker spellChecker = new SpellChecker(directory);
			
			Path dictonaryPath = Paths.get("/Users/aniket/Documents/workspace/luceneSetup/TestDictionary");
			
			/*
			 * To do :
			 * 
			 * The below field needs to be explored futher
			 * 
			 *https://lucene.apache.org/core/7_4_0/core/org/apache/lucene/index/IndexWriterConfig.html
			 *
			 * 
			 */
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig
		            .OpenMode.CREATE);//This  mandatory field helps in the creation of index.
			
			spellChecker.indexDictionary(new PlainTextDictionary(dictonaryPath),indexWriterConfig,false); 
			
			//String trialWord = "wrod";
			
			String trialWord ="WORD";
			
			
			int totalSuggestions = 3;
			
			/*
			 * To do :
			 * 
			 * Check the performance of ngrams against levenstein edit distance.
			 * 
			 */
			String [] suggestedWords = spellChecker.suggestSimilar(trialWord, totalSuggestions);//Uses Levenstein distance by default.
			
			if(suggestedWords!=null & suggestedWords.length>0) {
			for(String suggestedWord : suggestedWords) {
				System.out.println("The suggested word is" + suggestedWord);
			} } else {
				System.out.println("No suggestion available");
			}
			
			/*
			 * 
			 * 
			 * try case
			 * 
			 * the dictionary has a lower case word.
			 * 
			 */
			
			
			spellChecker.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
