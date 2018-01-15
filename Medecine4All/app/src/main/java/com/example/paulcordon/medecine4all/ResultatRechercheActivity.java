package com.example.paulcordon.medecine4all;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultatRechercheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String value = bundle.getString("text");
        assert value != null;
        value = value.toUpperCase();
        Log.d("value"," "+value);


        final Parser2 p=new Parser2();

        p.execute(value);

        setContentView(R.layout.activity_resultat_recherche);
        Button btn1= findViewById(R.id.btn1);
        Button btn2= findViewById(R.id.btn2);
        Button btn3= findViewById(R.id.btn3);
        Button btn4= findViewById(R.id.btn4);
        Button btn5= findViewById(R.id.btn5);
        Button btn6= findViewById(R.id.btn6);
        Button btn7= findViewById(R.id.btn7);
        Button btn8= findViewById(R.id.btn8);
        Button btn9= findViewById(R.id.btn9);
        Button btn10= findViewById(R.id.btn10);
        Button btn11= findViewById(R.id.btn11);
        Button btn12= findViewById(R.id.btn12);
        Button btn13= findViewById(R.id.btn13);
        Button btn14= findViewById(R.id.btn14);
        Button btn15= findViewById(R.id.btn15);
        Button btn16= findViewById(R.id.btn16);
        Button btn17= findViewById(R.id.btn17);
        Button btn18= findViewById(R.id.btn18);
        Button btn19= findViewById(R.id.btn19);
        Button btn20= findViewById(R.id.btn20);
        final ArrayList<Button> btnList=new ArrayList<>();
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);
        btnList.add(btn5);
        btnList.add(btn6);
        btnList.add(btn7);
        btnList.add(btn8);
        btnList.add(btn9);
        btnList.add(btn10);
        btnList.add(btn11);
        btnList.add(btn12);
        btnList.add(btn13);
        btnList.add(btn14);
        btnList.add(btn15);
        btnList.add(btn16);
        btnList.add(btn17);
        btnList.add(btn18);
        btnList.add(btn19);
        btnList.add(btn20);



        while(p.link1==null){

        }
        if(p.link1.size()==0){
            btn1.setText("Aucun resultat");
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ResultatRechercheActivity.this,MainActivity.class);
                    startActivity(in);
                }
            });
           
        }
        else if (p.link1.size()==1){
            Intent in=new Intent(ResultatRechercheActivity.this,InfoActivity.class);
            Bundle b = new Bundle();
            in.putExtra("text",p.link1.get(0));
            startActivity(in);
        }
        else{
            for(int i=0;i<p.link1.size() && i<btnList.size();i++){
                btnList.get(i).setText(p.link1.get(i));
                btnList.get(i).setVisibility(View.VISIBLE);


                btnList.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(ResultatRechercheActivity.this,InfoActivity.class);
                        Bundle b = new Bundle();
                        in.putExtra("text",((Button)v).getText());
                        startActivity(in);
                    }
                });

            }
        }
    }
}
