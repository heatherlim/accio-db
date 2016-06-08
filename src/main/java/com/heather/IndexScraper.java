package com.heather;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IndexScraper {
	
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
    	if(!text.toLowerCase().contains("gryffindor") && !text.toLowerCase().contains("slytherin") &&!text.toLowerCase().contains("hufflepuff") &&!text.toLowerCase().contains("ravenclaw") && !text.toLowerCase().contains("'s") && !text.toLowerCase().contains("boy") && !text.toLowerCase().contains("female") && !text.toLowerCase().contains("200") && !text.toLowerCase().contains("unidentified") && !text.toLowerCase().contains("student") && !text.toLowerCase().contains("unidentified") && !text.toLowerCase().contains("shady character") && !text.toLowerCase().contains("inquisitorial")){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private static Elements siteLinks() throws IOException{
    	return siteLinks("http://harrypotter.wikia.com/wiki/Category:Hogwarts_students?pagefrom=Shady+Character#mw-pages");
    }
    
    private static Elements siteLinks(String url) throws IOException{
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        return links;
    }
    
    private static void scrapedLinks() throws IOException{
    	int counter = 0;
        String lastEntry = "";
        
        for (Element link : siteLinks()) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        	if (link.text().contains("200") && !lastEntry.contains("200")){
        		counter += 1;
        		lastEntry = link.text();
        	} 
        	// does valid name need to be something like self.validname this.validname Scraper.validname etc?
        	if (counter == 1 && validName(link.text())){
        		lastEntry = link.text();
        		System.out.println(link.attr("abs:href") + " " + "(" + link.text() + ")");
        	} else if(counter == 2){
        		System.out.println(link.attr("abs:href"));
        		break;
        	}
        
        }
    }
	
	public static void main(String[] args) throws IOException {
        scrapedLinks();
    }
	
}
