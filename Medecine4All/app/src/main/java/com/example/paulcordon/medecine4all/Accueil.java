package com.example.paulcordon.medecine4all;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);



        final Button scan = (Button) findViewById(R.id.scan);
        final Button map = (Button) findViewById(R.id.map);

    scan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (Accueil.this, MainActivity.class );
            startActivity(intent);

        }
    });


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (Accueil.this, MapsActivity.class );
                startActivity(intent);

            }
        });
    }
}
