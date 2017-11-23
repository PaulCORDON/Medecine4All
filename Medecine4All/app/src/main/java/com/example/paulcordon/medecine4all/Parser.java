package com.example.paulcordon.medecine4all;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cordon Paul on 07/11/2017.
 */

class Parser {


    private String classe = "non trouve";
    private String molecule = "molecule";
    private String exipients = "exipients";
    private String statut = "statut";
    private String prix = "prix";
    private String labo = "laboratoire";
    private String taux ="taux de remboursement";



    private String indesirable ="";



    private String precaution = "";
    private boolean urlValide=true;


    boolean isUrlValide() {
        return urlValide;
    }
    String getPrix() {
        return prix;    }
    String getClasse() {return classe;}
    String getMolecule() {
        return molecule;
    }
    String getExipients() {
        return exipients;
    }
    String getStatut() {
        return statut;
    }
    String getLabo() {
        return labo;
    }
    String getTaux() {
        return taux;
    }
    String getIndesirable() {
        return indesirable;
    }
    String getPrecaution() {
        return precaution;
    }

    void execute (String url){
        final String urlCopie=url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("info","try");
                    String info;
                    String tabInfo[];
                    // Connection au site
                    Document doc = Jsoup.connect(urlCopie).get();

                    List<String> link = doc.getElementsByClass("medtab").eachText();

                    info=link.get(0);
                    info=info.replaceFirst("Classe thérapeutique","azertyuiop");
                    info=info.replaceFirst("Principes actifs","azertyuiop");
                    info=info.replaceFirst("Excipients","azertyuiop");
                    info=info.replaceFirst("Statut","azertyuiop");
                    info=info.replaceFirst("Prix de vente TTC","azertyuiop");
                    info=info.replaceFirst("Tx de remboursement SS","azertyuiop");
                    info=info.replaceFirst("Laboratoire","azertyuiop");
                    tabInfo=info.split("azertyuiop");

                    link = doc.getElementById("div_4").getElementsByClass("AmmListePuces1").eachText();
                    for (String s:link) {
                        indesirable+=s;
                    }
                    Log.d("indesirable","->"+indesirable);

                    link = doc.getElementById("div_2").getElementsByClass("AmmListePuces1").eachText();
                    for (String s:link) {
                        precaution+=s;
                    }
                    Log.d("precaution","->"+precaution);

                    if(tabInfo.length==7) {
                        classe = tabInfo[1];
                        molecule = tabInfo[2];
                        exipients = tabInfo[3];
                        statut =tabInfo[4];
                        taux =tabInfo[5];
                        labo = tabInfo[6];
                        Log.d("classe","->"+classe);
                        Log.d("molecule","->"+molecule);
                        Log.d("exipients","->"+exipients);
                        Log.d("statut","->"+statut);
                        Log.d("taux","->"+taux);
                        Log.d("labo","->"+labo);

                    }
                    else if(tabInfo.length==8) {
                        classe = tabInfo[1];
                        molecule = tabInfo[2];
                        exipients = tabInfo[3];
                        statut = tabInfo[4];
                        prix = tabInfo[5];
                        taux = tabInfo[6];
                        labo = tabInfo[7];
                        Log.d("classe","->"+classe);
                        Log.d("molecule","->"+molecule);
                        Log.d("exipients","->"+exipients);
                        Log.d("statut","->"+statut);
                        Log.d("prix","->"+prix);
                        Log.d("taux","->"+taux);
                        Log.d("labo","->"+labo);
                    }

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
