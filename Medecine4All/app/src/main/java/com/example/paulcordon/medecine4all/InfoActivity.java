package com.example.paulcordon.medecine4all;


import android.os.Bundle;
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
        /*value = value.toUpperCase();*/

        TextView nomMedic = (TextView) findViewById(R.id.nom);
        nomMedic.setText(value);

        Parser parser=new Parser();

        try{
            parser.getwebsite("http://www.doctissimo.fr/medicament-DOLIPRANE.htm");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("error","->"+e);

        }
        finally {
            TextView molecule= (TextView) findViewById(R.id.molecule);
            molecule.setText(parser.getMolecule());

            TextView taux = (TextView) findViewById(R.id.taux);
            taux.setText(parser.getTaux());

            TextView exipients = (TextView) findViewById(R.id.exipients);
            exipients.setText(parser.getExipients());

            TextView classe= (TextView) findViewById(R.id.classe);
            classe.setText(parser.getExipients());
        }

    }
}
