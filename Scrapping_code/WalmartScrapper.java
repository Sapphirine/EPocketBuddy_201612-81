import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//*******************************************************************************************************
/*
 * Author: Aman Shankar, Columbia University, Master's Student
 * Created on: 12/22/16
 * Revised on:
 * Email id: amanshankar9393@gmail.com
 * 
 */

//*********************************************************************************************************
public class WalmartScrapper {
	public static void main(String[] args)throws IOException  {
		ArrayList<String> Data= new ArrayList<>();
		 ArrayList<String> ImageUrl = new ArrayList<>();
		 
		 // Connects to the walmart today deals site
		Document d = Jsoup.connect("https://www.walmart.com/search/?query=todays%20deals&typeahead=todays%20d").timeout(6000).get();
	
		
		// selects the product and it's url
		Elements elements = d.select("a.js-product-title");
		Elements images = d.select("a.js-product-image");
		
		 // makes a list of project avalivale in today's deal
		for(Element elem : elements){
		
			Data.add(elem.text());
		}
			
		
		 // making a file out of the list, which will be used in the android application
		 PrintWriter pw = new PrintWriter(new File("C:\\Users\\Dell\\Documents\\test2.csv"));
		 StringBuilder sb = new StringBuilder();
		 
		 for(int i=0; i<Data.size();i++){
			 sb.append(Data.get(i));
			 sb.append(',');			 	 
		 }
		 pw.write(sb.toString());
	     pw.close();
	     System.out.println("done!"); 
	     
		
		
		
	}

}
