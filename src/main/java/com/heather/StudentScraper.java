package com.heather;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StudentScraper {
	
	
	public static void scrapedText() throws IOException{
        String url = "http://harrypotter.wikia.com/wiki/Astrix_A.";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements html = doc.select(".mw-content-text");
        String text = html.text();
        System.out.println(text);
	}
	
	public static void main(String[] args) throws IOException {
        scrapedText();
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
