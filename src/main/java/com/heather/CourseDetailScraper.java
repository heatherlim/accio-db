package com.heather;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseDetailScraper {
	
	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}


    private static void scrapedProfessor(String url) throws IOException{
        print("Fetching %s...", url);
	    Document doc = Jsoup.connect(url).get();
	    Element html = doc.select("div.pi-data-value").first();
		System.out.println(html);
    }
    
    private static void scrapedOWLS(String url) throws IOException{
    	// Search student db and find if student name matches the text(). If so, add the class as an O.W.L
        print("Fetching %s...", url);
	    Document doc = Jsoup.connect(url).get();
	    Elements html = doc.select(".lightbox-caption");
		System.out.println(html);
    }
    
    public static void main(String[] args) throws IOException {
		//scrapedProfessor("http://harrypotter.wikia.com/wiki/Care_of_Magical_Creatures");	
    	scrapedOWLS("http://harrypotter.wikia.com/wiki/Care_of_Magical_Creatures");
    }
}
