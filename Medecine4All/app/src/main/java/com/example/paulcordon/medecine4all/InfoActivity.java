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
    private TextToSpeech tts;
    Parser parser=new Parser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button efIndesirable= findViewById(R.id.EFIND);
        Button précaution=findViewById(R.id.PRECAUTION);



        TextToSpeech.OnInitListener listener =
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(final int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            Log.d("TTS ActInfo", "Text to speech engine started successfully.");
                            tts.setLanguage(Locale.FRANCE);
                        } else {
                            Log.d("TTS ActInfo", "Error starting the text to speech engine.");
                        }
                    }
                };
        tts = new TextToSpeech(this.getApplicationContext(), listener);


        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        String value = bundle.getString("text");
        assert value != null;
        value = value.toUpperCase();

        String url="http://www.doctissimo.fr/medicament-"+value+".htm";
        Log.d("value","->"+value);
        Log.d("url","->"+url);
        TextView nomMedic = findViewById(R.id.nom);
        nomMedic.setText(value);



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
        efIndesirable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tts.isSpeaking()){
                    tts.speak(parser.getIndesirable(), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                }
                else{
                    tts.stop();
                }

            }
        });
        précaution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tts.isSpeaking()){
                    tts.speak(parser.getPrecaution(), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                }
                else{
                    tts.stop();
                }
            }
        });


    }

}
