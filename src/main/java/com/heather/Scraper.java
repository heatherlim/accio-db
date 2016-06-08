package com.heather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Scraper {
	
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    private static Boolean validName(String text){
    	String answer = null;
    	if(text.toLowerCase().contains("(")){
    		answer = text.substring(text.indexOf("(")+1,text.indexOf(")"));
    	}
    	if(answer == "I" || answer == "II" || !text.contains("(") && !text.toLowerCase().contains("gryffindor") && !text.toLowerCase().contains("slytherin") &&!text.toLowerCase().contains("hufflepuff") &&!text.toLowerCase().contains("ravenclaw") && !text.toLowerCase().contains("'s") && !text.toLowerCase().contains("boy") && !text.toLowerCase().contains("female")){
    		return true;
    	} else {
    		return false;
    	}
    }
	
	public static void main(String[] args) throws IOException {
		
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "http://harrypotter.wikia.com/wiki/Category:Hogwarts_students";
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        
        print("\nLinks: (%d)", links.size());
        
        int counter = 0;
        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        	if (link.text().equals("next 200")){
        		counter += 1;	
        	}
        	// does valid name need to be something like self.validname this.validname Scraper.validname etc?
        	if (counter == 1 && validName(link.text())){
        		System.out.println(link.attr("abs:href") + " " + "(" + link.text() + ")");
        	}
        
        }
    }


	
}
