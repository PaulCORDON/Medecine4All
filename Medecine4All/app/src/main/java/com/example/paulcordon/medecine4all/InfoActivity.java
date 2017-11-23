package com.example.paulcordon.medecine4all;


import android.os.Bundle;
import android.os.Debug;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button efIndesirable= findViewById(R.id.EFIND);


        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("text");
        value = value.toUpperCase();

        String url="http://www.doctissimo.fr/medicament-"+value+".htm";
        Log.d("value","->"+value);
        Log.d("url","->"+url);
        TextView nomMedic = findViewById(R.id.nom);
        nomMedic.setText(value);


        Parser parser=new Parser();
        try{

            parser.execute(url);
            Log.d("info","try after parser");

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("error","->"+e);

        }

        while(parser.getClasse().equals("non trouve")&& parser.isUrlValide()){
            Log.d("info","after try catch info");

        }
        Log.d("info","after while urlValide ->"+parser.isUrlValide());
        TextView classe=  findViewById(R.id.classe);
        classe.setText(parser.getClasse());

        TextView molecule=  findViewById(R.id.molecule);
        molecule.setText(parser.getMolecule());

        TextView exipients =  findViewById(R.id.exipients);
        exipients.setText(parser.getExipients());

        TextView statut =  findViewById(R.id.Statut);
        statut.setText(parser.getStatut());

        TextView prix =  findViewById(R.id.prix);
        if(parser.getPrix().equals("prix")){
            prix.setVisibility(View.INVISIBLE);
            TextView titrePrix =  findViewById(R.id.Prix);
            titrePrix.setVisibility(View.INVISIBLE);
        }
        else{
            prix.setText(parser.getPrix());
        }



        TextView taux =  findViewById(R.id.taux);
        taux.setText(parser.getTaux());

        TextView labo =  findViewById(R.id.labo);
        labo.setText(parser.getLabo());


    }

}
