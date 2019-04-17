package com.google.android.gms.samples.vision.ocrreader;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Result extends AppCompatActivity {
    Intent intent;
    String value;
    TextView text;
    ArrayList<String> members = new ArrayList<String>();
    ArrayList<String> blacklist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        intent = getIntent();
        value = intent.getStringExtra("result"); //if it's a string you stored.

        text = (TextView) findViewById(R.id.textView);
        members.add("TN99F2378");
        members.add("DL49AK49");
        members.add("MH01AB1234");
        members.add("HR26DK8337");
        blacklist.add("MH20EJ0365");
        blacklist.add("MH12DE1433");
        blacklist.add("MH20V314");
        blacklist.add("MH2OV314");
        blacklist.add("DL2CJ1459");
        blacklist.add("DL3CC0524");
        blacklist.add("DL5SM2443");
        blacklist.add("HR38G6020");
        blacklist.add("MH01EA6837");
        blacklist.add("MHO1EA6837");
        blacklist.add("DL5SP2634");


        String result;

        try {
            result = "Car number is: "+value+"\n\n\n"+find(value);
        }
        catch (Exception e) {
            result = "";
        }

        if(result.isEmpty() || result == null)
            text.setText("Sorry no record found");
        else
            text.setText( result );
    }
    //function for searching the number plate
    public String find(String number) throws IOException {

        StringBuffer string = new StringBuffer();

        if(members.contains(number))
        {
            string.append("It is a Registered Car\n\nLet it pass by.\nMember valid till: 2020-05-3");
            LinearLayout l= (LinearLayout) findViewById(R.id.ll);
            l.setBackgroundColor(Color.GREEN);
            return string.toString();


        }
        if(blacklist.contains(number))
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                //deprecated in API 26
                v.vibrate(1500);
                for(int j=0;j<100000000;j++);
                v.vibrate(1500);

            string.append("It is a blacklisted car\n\nDeport ASAP");
            LinearLayout l= (LinearLayout) findViewById(R.id.ll);
            l.setBackgroundColor(Color.RED);
            return string.toString();

        }


        string.append("Not found in Records");
        LinearLayout l= (LinearLayout) findViewById(R.id.ll);
        l.setBackgroundColor(Color.WHITE);
        return string.toString();
    }
}
