package com.wikisexidentifier;

import projectutilities.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Controller
public class SexidentifierController {

	@RequestMapping("/")
	public ModelAndView homePage()  {
		ModelAndView modelandview = new ModelAndView("index");
		
		return modelandview;
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public ModelAndView result(@RequestParam("wiki_url") String wiki_url, @RequestParam("page_section_model") String page_section_model, @RequestParam("classifier_name") String classifier_name, @RequestParam("models_to_compare") String models_to_compare) throws Exception {
		
		String raw_media_wiki_content = new Scanner(new URL(wiki_url+"?action=raw").openStream(), "UTF-8").useDelimiter("\\A").next();
		
		WikipediaSpecialExportProcessor wsep = new WikipediaSpecialExportProcessor();
        ArrayList<String> pageParagraphs = wsep.getParagraphsFromMediaWikiString(raw_media_wiki_content);
		StanfordSpeechTaggerAndCounter sstc = new StanfordSpeechTaggerAndCounter();
        
		System.out.println(page_section_model);
		int paragraphs = Integer.parseInt(page_section_model);
		if (paragraphs == 0) paragraphs = pageParagraphs.size();
		
		ArrayList<String> tagged_paragraphs = new ArrayList<String>(pageParagraphs.size());
		
        for (String s : pageParagraphs) {
        	tagged_paragraphs.add(sstc.tagNormalizedString(s));
        }
        
        TermCountProbabilityModel tcpm = new TermCountProbabilityModel();
        ArrayList<String> pronouns = new ArrayList<String>();
        
        for (int i = 0; i < paragraphs; i++) {
        	String para = tagged_paragraphs.get(i);
        	String[] words = para.split(" ");
        	
        	for (String word : words) {
        		//System.out.println(word);
        		if (word.endsWith("_PRP")) {
        			String stripped = word.replace("_PRP", "");
        			String lo = stripped.toLowerCase();
        			tcpm.pushTerm(lo);
        			pronouns.add(lo);
        			//System.out.println(word);
        		} else if (word.endsWith("_PRP$")) {
        			String stripped = word.replace("_PRP$", "");
        			String lo = stripped.toLowerCase();
        			tcpm.pushTerm(lo);
        			pronouns.add(lo);
        		}
        	}
        }
        System.out.println(">> All Pronouns: " + pronouns);
        //System.out.println(tcpm.toString());
        if (tcpm.getModelSize() == 0) {
        	System.out.println("Required features used for the classifer not found");
        }
        
        String obj_filename = "", wom_filename = "", men_filename = "";
  
        int model_paragraphs = Integer.parseInt(models_to_compare);
        
        
//        switch (model_paragraphs) {
//        	default: {
//	        	obj_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/0/object.mdl";
//	        	wom_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/0/women.mdl";
//	        	men_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/0/men.mdl";
//	        	break;
//	        }
//	        case 1: {
//	        	obj_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/1/object.mdl";
//	        	wom_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/1/women.mdl";
//	        	men_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/1/men.mdl";
//	        	break;
//	        }
//	        case 2: {
//	        	obj_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/2/object.mdl";
//	        	wom_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/2/women.mdl";
//	        	men_filename = "/home/udaysagar/workspace_spring/wikiSexIdentifier/models/2/men.mdl";
//	        	break;
//	        }
//	    }
        
        switch (model_paragraphs) {
    	default: {
        	obj_filename = "/opt/tomcat/wikiSexIdentifier/models/0/object.mdl";
        	wom_filename = "/opt/tomcat/wikiSexIdentifier/models/0/women.mdl";
        	men_filename = "/opt/tomcat/wikiSexIdentifier/models/0/men.mdl";
        	break;
        }
        case 1: {
        	obj_filename = "/opt/tomcat/wikiSexIdentifier/models/1/object.mdl";
        	wom_filename = "/opt/tomcat/wikiSexIdentifier/models/1/women.mdl";
        	men_filename = "/opt/tomcat/wikiSexIdentifier/models/1/men.mdl";
        	break;
        }
        case 2: {
        	obj_filename = "/opt/tomcat/wikiSexIdentifier/models/2/object.mdl";
        	wom_filename = "/opt/tomcat/wikiSexIdentifier/models/2/women.mdl";
        	men_filename = "/opt/tomcat/wikiSexIdentifier/models/2/men.mdl";
        	break;
        }
    }        
        //System.out.println(obj_filename);
        TermCountProbabilityModel objects_model = new TermCountProbabilityModel(obj_filename);
        TermCountProbabilityModel women_model = new TermCountProbabilityModel(wom_filename);
        TermCountProbabilityModel men_model = new TermCountProbabilityModel(men_filename);
        
//        System.out.println("Objects");
//        System.out.println(objects_model.toString());
//        System.out.println("Women:");
//        System.out.println(women_model.toString());
//        System.out.println("Men");
//        System.out.println(men_model.toString());
        
        int vocab = 0;
        vocab += objects_model.getVocabulary().size();
        vocab += women_model.getVocabulary().size();
        vocab += men_model.getVocabulary().size();
        
        tcpm.computeTheTermProbabilites(vocab);
        
        double obj_prob = objects_model.getClassProbability(tcpm, vocab);
        double wom_prob = women_model.getClassProbability(tcpm, vocab);
        double men_prob = men_model.getClassProbability(tcpm, vocab);
        
        String winner = "";
        Double score;
        if (obj_prob > wom_prob) {
        	score = obj_prob;
        	winner = "Object";
        } else {
        	score = wom_prob;
        	winner = "Female";
        }
        
        if (men_prob > score) {
        	score = men_prob;
        	winner = "Male";
        }
        
        //ArrayList<Double> arrayListOfProbs = new ArrayList<Double>();
        
       //Collections.sort(arrayListOfProbs);
        
		//FileSystemResource male_model = new FileSystemResource("/WEB-INF/models/0/");		
		
//		String[] result = {"Object Class Score:80%", "Female Class Score:60%", "Male Class Score:40%"};
//		String[] first_result = result[0].split(":");
//		String labelOf_first = first_result[0];
		//String probabilityOf_first = first_result[1];
		String probabilityOf_first = "" + obj_prob;

//		String[] second_result = result[1].split(":");
//		String labelOf_second = second_result[0];
		//String probabilityOf_second = second_result[1];
		String probabilityOf_second = "" + wom_prob;		
		
//		String[] third_result = result[2].split(":");
//		String labelOf_third = third_result[0];
		//String probabilityOf_third = third_result[1];
		String probabilityOf_third = "" + men_prob;		
		
		ModelAndView modelandview = new ModelAndView("result");
		modelandview.addObject("labelOf_first", "Object Class Score");
		modelandview.addObject("Score_First", probabilityOf_first);		

		modelandview.addObject("labelOf_second", "Female Class Score");
		modelandview.addObject("Score_Second", probabilityOf_second);
		
		modelandview.addObject("labelOf_third", "Male Class Score");
		modelandview.addObject("Score_Third", probabilityOf_third);
		
		modelandview.addObject("wiki_url", wiki_url);		
		modelandview.addObject("winner", winner);		
		
		return modelandview;
	}	
}
