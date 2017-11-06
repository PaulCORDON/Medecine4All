package com.example.paulcordon.medecine4all;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by paul cordon on 25/10/2017.
 */

class Parser {

    private String info;
    private String tabInfo[];
    private String classe = "classe therapeutique";
    private String molecule = "molecule";
    private String exipients = "exipients";
    private String statut = "statut";
    private String prix = "prix";
    private String labo = "laboratoire";
    private String taux ="taux de remboursement";

    public String getPrix() {
        return prix;    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    String getMolecule() {
        return molecule;
    }

    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    String getExipients() {
        return exipients;
    }

    public void setExipients(String exipients) {
        this.exipients = exipients;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getLabo() {
        return labo;
    }

    public void setLabo(String labo) {
        this.labo = labo;
    }

    String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    void getwebsite(String url){
        try {
            // Connection au site


            Document doc = Jsoup.connect("http://www.doctissimo.fr/medicament-QUINAPRIL-WINTHROP.htm").get();
            // on récupère les les éléments de la classe AmmCorpsTexte


            List<String> link = doc.getElementsByClass("medtab").eachText();
            System.out.println(link.get(0));
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


}
