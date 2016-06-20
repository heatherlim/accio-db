package com.heather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StudentScraper {
	
	
	public static String scrapedText() throws IOException{
        String url = "http://harrypotter.wikia.com/wiki/Filius_Flitwick";
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
	
	public static void studentGender(List studentDet) {
		int detailIndex = studentDet.indexOf("Gender") + 1;
		Object gender = studentDet.get(detailIndex);
		System.out.println(gender);
	}
	
	public static void studentSpecies(List studentDet){
		
		// If gender index and species index has a difference of 3?
		int genderIndex = studentDet.indexOf("Gender");
		int detailIndex = studentDet.indexOf("Species") + 1;
		
		if (genderIndex - detailIndex >= 3){
			for (int i = detailIndex; i < genderIndex; i ++){
				Object species = studentDet.get(i);
				System.out.println(species);
			}
		} else {
			System.out.println(studentDet.get(detailIndex));
		}
		
	}
	
	public static void studentHouse(List studentDet) {
		int detailIndex = studentDet.indexOf("House") + 1;
		String house = studentDet.get(detailIndex).toString().replaceAll("\\[.*\\]", "");
		
		if (house.equals("Gryffindor") || house.equals("Hufflepuff") || house.equals("Ravenclaw") || house.equals("Slytherin")){
			System.out.println(house);
		} else {
			System.out.println("Unknown");
		}
		
	}
	
	public static void studentBlood(List studentDet) {
		if (studentDet.contains("Blood")){
			int detailIndex = studentDet.indexOf("Blood") + 2;
			String blood = ((String) studentDet.get(detailIndex)).toString().replaceAll(",","");
			System.out.println(blood.replaceAll("\\[.*\\]", ""));
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
