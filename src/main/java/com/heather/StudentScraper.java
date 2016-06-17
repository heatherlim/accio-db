package com.heather;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StudentScraper {
	
	
	public static String scrapedText() throws IOException{
        String url = "http://harrypotter.wikia.com/wiki/Astrix_A.";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements html = doc.select(".mw-content-text");
        String text = html.text();
        return text;
	}
	
	public static List studentDetailsList() throws IOException {
		String unparsed = scrapedText();
		String array[] = unparsed.split(" ");
		return Arrays.asList(array);
	}
	
	public static void studentDetails() throws IOException{
		List list = studentDetailsList();
		int detailIndex = list.indexOf("Gender") + 1;
		Object gender = list.get(detailIndex);
		System.out.println(gender);
	}
	
	public static void main(String[] args) throws IOException {
        studentDetails();
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
