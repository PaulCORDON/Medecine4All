package com.example.paulcordon.medecine4all;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cordon Paul on 07/11/2017.
 */

 class Parser2 {

     List<String> link1;
    public boolean enCour =true;
     private boolean urlValide=true;
    boolean isUrlValide() {
        return urlValide;
    }


    void execute (String url){
        final String recherche=url.toLowerCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                link1= new ArrayList<String>();
                try {
                    Document doc;
                    Log.d("je suis dans parser2 ", "avant connect"+recherche);
                    doc=Jsoup.connect("http://www.doctissimo.fr/recherche-medicament.htm?medicine_search%5Bquery%5D="+recherche).get();

                    for(Element s:doc.getElementsByClass("multi-columns-2")) {
                        Log.d("dans le for","coucou"+s);
                        link1=s.getElementsByAttribute("href").eachText();
                    }
                    enCour =false;
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.d("error","->"+e);
                    urlValide=false;
                }

            }


        }).start();

    }


}
