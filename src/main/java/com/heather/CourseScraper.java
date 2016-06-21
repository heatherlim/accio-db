package com.heather;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseScraper {

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    private static Elements siteLinks(String url) throws IOException{
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements html = doc.select("figure + ul");
		return html.select("a[href]");
    }
    
    
    private static Boolean validName(String text){
    	if (!text.contains("school year") && !text.contains(".jpg") && !text.equals("") && !text.contains("1")){
    		return true;
    	} else {
    		return false;
    	}
    }
    
   
    
	public static void main(String[] args) throws IOException {
		for (Element link : siteLinks("http://harrypotter.wikia.com/wiki/Hogwarts_subjects")){
			if(validName(link.text())){
				// Class creation can go here
				System.out.println(link.attr("abs:href") + " " + link.text());
			}
		}
    }
}
