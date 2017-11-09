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


    private String classe = "classe therapeutique";
    private String molecule = "molecule";
    private String exipients = "exipients";
    private String statut = "statut";
    private String prix = "prix";
    private String labo = "laboratoire";
    private String taux ="taux de remboursement";

    String getPrix() {
        return prix;    }
    String getClasse() {
        return classe;
    }
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


    void execute (String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("info","try");
                    String info;
                    String tabInfo[];
                    // Connection au site
                    Document doc = Jsoup.connect("http://www.doctissimo.fr/medicament-DOLIPRANE.htm").get();

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
                    if(tabInfo.length==7) {
                        Log.d("classe","->"+tabInfo[1]);
                        Log.d("molecule","->"+tabInfo[2]);
                        Log.d("exipients","->"+tabInfo[3]);
                        Log.d("statut","->"+tabInfo[4]);
                        Log.d("taux","->"+tabInfo[5]);
                        Log.d("labo","->"+tabInfo[6]);
                        classe = tabInfo[1];
                        molecule = tabInfo[2];
                        exipients = tabInfo[3];
                        statut =tabInfo[4];
                        taux =tabInfo[5];
                        labo = tabInfo[6];
                    }
                    else if(tabInfo.length==8) {
                        classe = tabInfo[1];
                        molecule = tabInfo[2];
                        exipients = tabInfo[3];
                        statut = tabInfo[4];
                        prix = tabInfo[5];
                        taux = tabInfo[6];
                        labo = tabInfo[7];
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.d("error","->"+e);
                }

            }


        }).start();

    }


}
