/**
 * 
 */
package com.ani.stanfordnlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.coref.docreader.CoNLLDocumentReader.NamedEntityAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.MentionsAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author aniket
 * 
 * Note : jar file stanford-core-nlp-3.8 models.jar needs to be added explicitly in the class path,as there is 
 * no standard way to specify a classifier.
 * 
 * In case of maven projects, <classifier>models</classifier> needs to be specified in core-nlp dependency
 * 
 * Basic program to understand the various tagging techniques offered by Standford NLP library.
 * 
 * 
 * Tagging includes POS, NER,
 * 
 * Pipeline of ("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref") is 
 * created and the text is passed through it.
 * 
 * The text can also be passed through individual models as well like ner
 * 
 * 
 * 
 * {@link https://interviewbubble.com/getting-started-with-stanford-corenlp-a-stanford-corenlp-tutorial/}
 * 
 * {@link https://interviewbubble.com/getting-started-with-stanford-corenlp/}
 * 
 * 

 * 
 *
 */
public class StandfordNLPModels {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = new Properties();

        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
     // read some text in the text variable
        String text = "She went to America last week.";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        //interpretTheOutput(document);
       // generateDepedencyTree(document);
        entityMentionsTest();
        System.out.println( "End of Processing" );
    }
	
	
	
	/*
	 * This output of the Annotation object can be obtained using CoreLabel and CoreMap class
	 * 
	 * 
	 */
	public static void interpretTheOutput (Annotation document) {
		
		// these are all the sentences in this document
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        List<String> words = new ArrayList<>();
        List<String> posTags = new ArrayList<>();
        List<String> nerTags = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                // this is the text of the token
            	System.out.println("Token under processing is : "+ token);
                String word = token.get(TextAnnotation.class);
                words.add(word);
                // this is the POS tag of the token
                String pos = token.get(PartOfSpeechAnnotation.class);
                posTags.add(pos);
                // this is the NER label of the token
                String ne = token.get(NamedEntityTagAnnotation.class);
                nerTags.add(ne);
            }
        }
        System.out.println("sentences ::" + sentences.toString());
        System.out.println("words ::"  + words.toString());
        System.out.println("part of speech tagging ::" +posTags.toString());
        System.out.println("Named Entity Recognization" + nerTags.toString());

	}

	/*
	 * 
	 * Create a dependency tree and syntactic tree  of the sentences in the document
	 * 
	 * 
	 */

	
	public static void generateDepedencyTree(Annotation document) {
		
		
        List <CoreMap> sentences = document.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
        	// This is the syntactic parse tree of sentence 

            Tree tree = sentence.get(TreeAnnotation.class); 

            System.out.println("Tree:\n"+ tree); 
            
            
            
         // This is the dependency graph of the sentence 

            SemanticGraph dependencies = sentence.get(CollapsedDependenciesAnnotation.class); 
            
            SemanticGraph enhanceddependencies = sentence.get(EnhancedDependenciesAnnotation.class);

            SemanticGraph enhancedPlusplusdependencies = sentence.get(EnhancedPlusPlusDependenciesAnnotation.class);

            System.out.println("Dependencies\n:"+ dependencies);
            System.out.println("Enhanceddependencies \n:"+enhanceddependencies);

            System.out.println("EnhancedPlusplusdependencies \n:"+enhancedPlusplusdependencies);

        }
        }

	

	
/*
 * Testing entityMentions annotator and its significance
 * 
 * 
 */

public static void entityMentionsTest() {
	
	
	String testString = "Joe Smith went to America";
	//creating the pipeline first :
	Properties props = new Properties();

    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, entitymentions");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    
 // read some text in the text variable
    String text = "She went to America last week.";

    // create an empty Annotation just with the given text
    Annotation document = new Annotation(testString);

    // run all Annotators on this text
    pipeline.annotate(document);
    
 // these are all the sentences in this document
   // List<CoreMap> sentences = document.get(SentencesAnnotation.class);

    List<CoreMap> mutliwordexpr = document.get(MentionsAnnotation.class); 
    
    for(CoreMap multiword : mutliwordexpr) {
    	System.out.println(multiword);
    	String custner = multiword.get(NamedEntityTagAnnotation.class);//this is for recognizing any named entity
    	System.out.println("Entity is " + custner);
    	
    }
    
}
}