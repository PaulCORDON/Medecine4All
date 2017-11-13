package com.example.paulcordon.medecine4all;


import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;




public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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


        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("error","->"+e);

        }
        finally {
            TextView classe= (TextView) findViewById(R.id.classe);
            classe.setText(parser.getClasse());

            TextView molecule= (TextView) findViewById(R.id.molecule);
            molecule.setText(parser.getMolecule());

            TextView exipients = (TextView) findViewById(R.id.exipients);
            exipients.setText(parser.getExipients());

            TextView prix = (TextView) findViewById(R.id.prix);
            prix.setText(parser.getPrix());

            TextView taux = (TextView) findViewById(R.id.taux);
            taux.setText(parser.getTaux());

            TextView labo = (TextView) findViewById(R.id.labo);
            labo.setText(parser.getLabo());

        }

    }
}
