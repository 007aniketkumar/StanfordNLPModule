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
	 * 
	 * /Users/aniket/Documents/workspace/luceneIndexTestOnly
	 *
	 */
	
	public static void main(String[] args) {

		
		Path dir =  Paths.get("/Users/aniket/Documents/workspace/luceneSetup/luceneIndexTestOnly");
        try {
			Directory directory = FSDirectory.open(dir);
			
			SpellChecker spellChecker = new SpellChecker(directory);
			
			Path dictonaryPath = Paths.get("/Users/aniket/Documents/workspace/luceneSetup/TestDictionary");
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig
		            .OpenMode.CREATE);
			spellChecker.indexDictionary(new PlainTextDictionary(dictonaryPath),indexWriterConfig,false); 
			
			String trialWord = "wrod";
			
			int totalSuggestions = 3;
			
			String [] suggestedWords = spellChecker.suggestSimilar(trialWord, totalSuggestions);
			
			if(suggestedWords!=null & suggestedWords.length>0) {
			for(String suggestedWord : suggestedWords) {
				System.out.println("The suggested word is" + suggestedWord);
			} } else {
				System.out.println("No suggestion available");
			}
			
			spellChecker.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
