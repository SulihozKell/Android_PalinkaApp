package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText felvetelFozo, felvetelGyumolcs, felvetelAlkoholtartalom;
    private Button btnFelvetel, felvetelrolVissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();

        btnFelvetel.setOnClickListener(v -> {
            String fozo = felvetelFozo.getText().toString().trim();
            String gyumolcs = felvetelGyumolcs.getText().toString().trim();
            String alkoholtartalomString = felvetelAlkoholtartalom.getText().toString().trim();
            if (fozo.isEmpty() || gyumolcs.isEmpty() || alkoholtartalomString.isEmpty()) {
                Toast.makeText(getApplicationContext(), "A mezők értéke nem lehet üres!", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    int alkoholtartalom = Integer.parseInt(alkoholtartalomString);
                    if (adatbazis.felvetel(fozo, gyumolcs, alkoholtartalom)) {
                        Toast.makeText(getApplicationContext(), "Sikeres rögzítés!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Sikertelen rögzítés!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Az alkoholtartalomnak számnak kell lennie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        felvetelrolVissza.setOnClickListener(v -> {
            Intent mainre = new Intent(AdatFelvetelActivity.this, MainActivity.class);
            startActivity(mainre);
            finish();
        });
    }

    private void init() {
        felvetelFozo =findViewById(R.id.felvetel_fozo);
        felvetelGyumolcs =findViewById(R.id.felvetel_gyumolcs);
        felvetelAlkoholtartalom =findViewById(R.id.felvetel_alkoholtartalom);
        btnFelvetel =findViewById(R.id.btn_felvetel);
        felvetelrolVissza =findViewById(R.id.felvetelrol_vissza);
        adatbazis = new DBHelper(this);
    }
}