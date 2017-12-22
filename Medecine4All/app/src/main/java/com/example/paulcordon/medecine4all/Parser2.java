package com.example.paulcordon.medecine4all;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cordon Paul on 07/11/2017.
 */

 class Parser2 {

   List<String> link1=null;
    private boolean urlValide=true;


    boolean isUrlValide() {
        return urlValide;
    }


    void execute (String url){
        final String recherche=url;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc;
                    doc=Jsoup.connect("http://sante.doctissimo.fr/medicaments/recherche-medicament.htm").data("search",recherche).data("but_go.x","59").data("but_go.y","14").data("but_go","ok").post();
                    link1 = doc.getElementsByClass("lien_bleu").eachText();

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
