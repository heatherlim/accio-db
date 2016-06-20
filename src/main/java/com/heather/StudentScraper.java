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
        String url = "http://harrypotter.wikia.com/wiki/Dominique_Weasley";
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
	
	public static void studentGender(List studentDet) throws IOException{
		int detailIndex = studentDet.indexOf("Gender") + 1;
		Object gender = studentDet.get(detailIndex);
		System.out.println(gender);
	}
	
	public static void studentSpecies(List studentDet) throws IOException{
		
		// If gender index and species index has a difference of 3?
		int genderIndex = studentDet.indexOf("Gender") + 1;
		int detailIndex = studentDet.indexOf("Species") + 1;
		
		if (genderIndex - detailIndex >= 3){
			for (int i = detailIndex; i < genderIndex; i ++){
				Object species = studentDet.get(i);
				System.out.println(species);
			}
		} 
		
	}
	
	public static void studentHouse(List studentDet) throws IOException{
		int detailIndex = studentDet.indexOf("House") + 1;
		Object house = studentDet.get(detailIndex);
		
		if (studentDet.contains("House") && (house.toString() == "Gryffindor" || house.toString() == "Hufflepuff" || house.toString() == "Ravenclaw" || house.toString() == "Slytherin")){
			System.out.println(((String) house).replaceAll("\\[.*\\]", ""));
		} else {
			System.out.println("Unknown");
		}
		
	}
	
	public static void studentBlood(List studentDet) throws IOException{
		if (studentDet.contains("Blood")){
			int detailIndex = studentDet.indexOf("Blood") + 2;
			Object blood = ((String) studentDet.get(detailIndex)).replaceAll(",","");
			System.out.println(blood);
		} else {
			System.out.println("Unknown");
		}
	}
	
	public static void studentDetails() throws IOException{
		List list = studentDetailsList();
		studentGender(list);
		studentSpecies(list);
		studentHouse(list);
		studentBlood(list);
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
