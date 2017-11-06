package com.example.paulcordon.medecine4all;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by paul cordon on 25/10/2017.
 */

public class Parser {


    private String classe = "classe therapeutique";
    private String molecule = "molecule";
    private String exipients = "exipients";
    private String statut = "statut";
    private String labo = "laboratoire";
    private String taux ="taux de remboursement";

    private Document doc = null;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMolecule() {
        return molecule;
    }

    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    public String getExipients() {
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

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public void getwebsite(String url){
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.title();

           //On trouve la molecule.
            int nbTiret = 0;
            int start = 0, end = 0 ;
            for (int i= 0 ; i< title.length() && nbTiret<2 ; i++) {
                if (title.charAt(i) == '-') {
                    nbTiret++;
                    if (nbTiret == 1) {
                        start = i;
                    }
                    if (nbTiret == 2) {
                        end = i;
                    }
                }
            }
            char [] tabChar = title.toCharArray();
            this.setMolecule(new String (tabChar,start+1,end-start-1 ));
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("error","->"+e);
        }
    }

    /**    Document doc = null;
     try {

     // Connection au site
     doc = Jsoup.connect("http://www.doctissimo.fr/medicament-DOLIPRANE.htm").get();
     // R�cuperation du titre de la page et affichage
     String title = doc.title();
     String mot = null;
     int nbTiret = 0;
     int start = 0, end = 0 ;
     for (int i= 0 ; i< title.length()&& nbTiret<2;i++) {
     if ( title.charAt(i) == '-' ) {
     nbTiret ++ ;
     if(nbTiret==1) {
     start = i;
     }
     if(nbTiret==2) {
     end = i;
     }
     }
     }
     char [] tabChar = title.toCharArray();
     mot = new String (tabChar,start+1,end-start-1 );
     TextView nomMolecule = (TextView) findViewById(R.id.textView4);
     nomMolecule.setText(mot);



     } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     **/



}
