package com.wikisexidentifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.net.*;
import java.util.Scanner;
@Controller
public class SexidentifierController {

	@RequestMapping("/")
	public ModelAndView homePage()  {
		ModelAndView modelandview = new ModelAndView("index");
		
		return modelandview;
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public ModelAndView result(@RequestParam("wiki_url") String wiki_url, @RequestParam("page_section_model") String page_section_model, @RequestParam("classifier_name") String classifier_name) throws Exception {
		
		String raw_media_wiki_content = new Scanner(new URL(wiki_url+"?action=raw").openStream(), "UTF-8").useDelimiter("\\A").next();		
		ModelAndView modelandview = new ModelAndView("result");
		modelandview.addObject("wiki_url", raw_media_wiki_content);
		modelandview.addObject("page_section_model", page_section_model);		
		modelandview.addObject("classifier_name", classifier_name);		
		
		return modelandview;
	}	
}
