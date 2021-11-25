package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button palinkaFelvetele, palinkaKeresese, palinkaListazasa;
    TextView palinkaLista;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        palinkaLista.setMovementMethod(new ScrollingMovementMethod());

        palinkaFelvetele.setOnClickListener(v -> {
            Intent felvetelre = new Intent(MainActivity.this, AdatFelvetelActivity.class);
            startActivity(felvetelre);
            finish();
        });

        palinkaKeresese.setOnClickListener(v -> {
            Intent keresesre = new Intent(MainActivity.this, AdatKeresesActivity.class);
            startActivity(keresesre);
            finish();
        });

        palinkaListazasa.setOnClickListener(v -> {
            Cursor adatok = adatbazis.listaz();
            if (adatok.getCount() == 0) {
                Toast.makeText(this, "Üres az adatbázis!", Toast.LENGTH_SHORT).show();
            }
            else {
                StringBuilder stringBuilder = new StringBuilder();
                while (adatok.moveToNext()) {
                    stringBuilder.append("Id: ").append(adatok.getInt(0)).append(System.lineSeparator());
                    stringBuilder.append("Főző: ").append(adatok.getString(1)).append(System.lineSeparator());
                    stringBuilder.append("Gyümölcs: ").append(adatok.getString(2)).append(System.lineSeparator());
                    stringBuilder.append("Alkoholtartalom: ").append(adatok.getInt(3)).append(System.lineSeparator());
                    stringBuilder.append(System.lineSeparator());
                }
                palinkaLista.setText(stringBuilder.toString());
                Toast.makeText(this, "Sikeres listázás!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        palinkaFelvetele =findViewById(R.id.palinka_felvetele);
        palinkaKeresese =findViewById(R.id.palinka_keresese);
        palinkaListazasa =findViewById(R.id.palinka_listazasa);
        palinkaLista =findViewById(R.id.palinka_lista);
        adatbazis = new DBHelper(MainActivity.this);
    }
}