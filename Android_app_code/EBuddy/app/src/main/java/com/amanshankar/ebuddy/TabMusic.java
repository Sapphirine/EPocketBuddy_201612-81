package com.amanshankar.ebuddy;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class TabMusic extends Fragment {
    String username;

    public String readFromCsv(InputStream i, String userId) throws Exception {

        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new InputStreamReader(i));
            while ((sCurrentLine = br.readLine()) != null) {
                if(userId.equals(sCurrentLine.split(",")[0])){
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
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);

        InputStream inputStream = getResources().openRawResource(R.raw.recmusic);
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
        String[] music = Arrays.copyOfRange(testing, 1, testing.length);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,music);
        //adapter = new ArrayAdapter(getActivity(), android.R.layout.activity_list_item, shoppingList);
        ListView lv = (ListView) rootView.findViewById(R.id.listView);
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
                intent.putExtra(SearchManager.QUERY, selectedItem +" Band");
                startActivity(intent);



            }
        });
        return rootView;
    }
}
