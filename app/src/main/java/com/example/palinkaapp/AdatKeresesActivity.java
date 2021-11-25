package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {
    private EditText keresesFozo, keresesGyumolcs;
    private Button btnKereses, keresesrolVissza;
    private TextView palinkaKeresesKiir;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);

        init();

        btnKereses.setOnClickListener(v -> {
            String fozo = keresesFozo.getText().toString().trim();
            String gyumolcs = keresesGyumolcs.getText().toString().trim();
            Cursor adatok = adatbazis.kereses(fozo, gyumolcs);

            if (fozo.isEmpty() || gyumolcs.isEmpty()) {
                Toast.makeText(getApplicationContext(), "A mezők értéke nem lehet üres!", Toast.LENGTH_SHORT).show();
            }
            else {
                StringBuilder stringBuilder = new StringBuilder();
                while (adatok.moveToNext()) {
                    stringBuilder.append("Alkoholtartalom: ").append(adatok.getInt(0)).append(" %").append(System.lineSeparator());
                    stringBuilder.append(System.lineSeparator());
                }
                if (stringBuilder.toString() == "") {
                    palinkaKeresesKiir.setText("A megadott adatokkal nem található pálinka!");
                }
                else {
                    palinkaKeresesKiir.setText(stringBuilder.toString());
                }
            }
        });

        keresesrolVissza.setOnClickListener(v -> {
            Intent mainre = new Intent(AdatKeresesActivity.this, MainActivity.class);
            startActivity(mainre);
            finish();
        });
    }

    private void init() {
        keresesFozo =findViewById(R.id.kereses_fozo);
        keresesGyumolcs =findViewById(R.id.kereses_gyumolcs);
        btnKereses =findViewById(R.id.btn_kereses);
        keresesrolVissza =findViewById(R.id.keresesrol_vissza);
        palinkaKeresesKiir =findViewById(R.id.palinka_kereses_kiir);
        adatbazis = new DBHelper(this);
    }
}