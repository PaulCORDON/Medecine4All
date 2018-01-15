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
    private String molecule = "Non trouvée";
    private String exipients = "Non trouvés";
    private String statut = "Non trouvé";
    private String prix = "prix";
    private String labo = "Non trouvé";
    private String taux ="Non trouvé";

    private boolean classeIsPresent = false;
    private boolean moleculeIsPresent= false;
    private boolean exipientIsPresent = false;
    private boolean statutIsPresent = false;
    private boolean prixIsPresent = false;
    private boolean laboIsPresent = false;
    private boolean tauxIsPresent = false;

    private int location=1;
    private int classeLocation;
    private int moleculeLocation;
    private int exipientLocation;
    private int statutLocation;
    private int prixLocation;
    private int laboLocation;
    private int tauxLocation;

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
                    if(info.contains("Classe thérapeutique")){
                        classeIsPresent=true;
                        classeLocation=location;
                        location++;
                        info=info.replaceFirst("Classe thérapeutique","azertyuiop");
                    }
                    if(info.contains("Principes actifs")){
                       moleculeIsPresent=true;
                       moleculeLocation=location;
                       location++;
                       info=info.replaceFirst("Principes actifs","azertyuiop");
                    }
                    if(info.contains("Excipients")){
                        exipientIsPresent=true;
                        exipientLocation=location;
                        location++;
                        info=info.replaceFirst("Excipients","azertyuiop");
                    }
                    if(info.contains("Statut")){
                        statutIsPresent=true;
                        statutLocation=location;
                        location++;
                        info=info.replaceFirst("Statut","azertyuiop");
                    }
                    if(info.contains("Prix de vente TTC")){
                       prixIsPresent=true;
                       prixLocation=location;
                       location++;
                       info=info.replaceFirst("Prix de vente TTC","azertyuiop");
                    }
                    if(info.contains("Tx de remboursement SS")){
                        tauxIsPresent=true;
                        tauxLocation=location;
                        location++;
                        info=info.replaceFirst("Tx de remboursement SS","azertyuiop");
                    }
                    if(info.contains("Laboratoire")){
                        laboIsPresent=true;
                        laboLocation=location;
                        location++;
                        info=info.replaceFirst("Laboratoire","azertyuiop");
                    }

                    tabInfo=info.split("azertyuiop");

                    link = doc.getElementById("div_4").getElementsByClass("AmmListePuces1").eachText();
                    if(link.size()==0){
                        indesirable="pas d'effets indesirables trouvés";
                    }
                    else{
                        for (String s:link) {
                            indesirable+=s;
                        }
                    }
                    Log.d("indesirable","->"+indesirable);

                    link = doc.getElementById("div_2").getElementsByClass("AmmListePuces1").eachText();
                    if(link.size()==0){
                        precaution="pas de précautions";
                    }
                    else{
                        for (String s:link) {
                            precaution+=s;
                        }
                    }

                    Log.d("precaution","->"+precaution);

                    if(classeIsPresent){
                        classe = tabInfo[classeLocation];
                        Log.d("classe","->"+classe);
                    }
                    if(moleculeIsPresent){
                        molecule = tabInfo[moleculeLocation];
                        Log.d("molecule","->"+molecule);
                    }
                    if(exipientIsPresent){
                        exipients = tabInfo[exipientLocation];
                        Log.d("exipients","->"+exipients);
                    }
                    if(statutIsPresent){
                        statut = tabInfo[statutLocation];
                        Log.d("statut","->"+statut);
                    }
                    if(prixIsPresent){
                        prix = tabInfo[prixLocation];
                        Log.d("prix","->"+prix);
                    }
                    if(tauxIsPresent){
                        taux = tabInfo[tauxLocation];
                        Log.d("taux","->"+taux);
                    }
                    if(laboIsPresent) {
                        labo = tabInfo[laboLocation];
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
