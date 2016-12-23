package com.amanshankar.ebuddy;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Dell on 15-12-2016.
 */
public class TabWalmart extends Fragment {


    String username;

    public String readFromCsv(InputStream i, String userId) throws Exception {

        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new InputStreamReader(i));
            while ((sCurrentLine = br.readLine()) != null) {
                {
                    System.out.println(sCurrentLine);
                    return sCurrentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_walmart, container, false);

        InputStream inputStream = getResources().openRawResource(R.raw.test1);
        String test="";
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            username = bundle.getString("username");
        }

        try {
            test = readFromCsv(inputStream,username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String [] testing = test.split(",");
        String[] deals = Arrays.copyOfRange(testing, 1, testing.length);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,deals);
        //adapter = new ArrayAdapter(getActivity(), android.R.layout.activity_list_item, shoppingList);
       ListView lv = (ListView) rootView.findViewById(R.id.listView1);
        lv.setAdapter(adapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                String selectedItem = ((TextView) arg1).getText().toString();
                ;
                //You could lookup by position, but "name" is more general
                System.out.println("cliked");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String keyword = "your query here";
                intent.putExtra(SearchManager.QUERY, "Walmart deals "+selectedItem);
                startActivity(intent);


            }
        });

        return rootView;
    }

    /*

   private String[] ScrapeWalmart() throws IOException {

       ArrayList<String> Data = new ArrayList<>();
      // Document d = Jsoup.connect("https://www.walmart.com/search/?query=todays%20deals&typeahead=todays%20d").timeout(6000).get();
      // Elements ele = d.select("div#cui-content");
       //Elements elements = d.select("a.js-product-title");
       //Elements images = d.select("img.product-image");
 /*
       for(Element elem : ele){
           //String daata = elem.select("a[href]");
           //System.out.println(daata);
           Data.add(elem.text());
           System.out.println(elem.text());
       }


       String [] d1 = (String[])Data.toArray();
        return d1;

   } */
}
