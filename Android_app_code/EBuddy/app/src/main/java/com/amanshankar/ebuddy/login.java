package com.amanshankar.ebuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dell on 14-12-2016.
 */
public class login extends Activity {



    private TextView resultText2;

    String[] usePass ={"1,1234","2,abcd","3,abcdfe"};



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        resultText2 = (TextView) findViewById(R.id.textView2);

    }

    public boolean checkUserPass(String[] s){
        String user = s[0];
        String pass = s[1];
        String check = s[0]+","+s[1];

        for (String s1 : usePass){
            if(s1.equals(check)) return  true;
        }
        return false;
    }




    public void OnButtonClick(View V){
        if(V.getId()==R.id.btnLogin){
            EditText userName = (EditText)findViewById(R.id.tfUserName);
            String usrName = userName.getText().toString();
            EditText password = (EditText)findViewById(R.id.tfPassword);
            String pasword = password.getText().toString();
           // final String json= formatDataasJSON(usrName,pasword);
           // System.out.print(json);
            String [] s = {usrName,pasword};
            if(checkUserPass(s)) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("username",usrName);
                startActivity(i);
                System.out.println("ddfsdfs "+ usrName);

            }

        }

    }




}
