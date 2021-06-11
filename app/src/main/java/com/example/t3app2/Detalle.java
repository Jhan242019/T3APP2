package com.example.t3app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t3app2.entities.Pokemon;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ImageView imagen = findViewById(R.id.imagenPokemon);
        TextView nombre = findViewById(R.id.nombreP);
        TextView tipo = findViewById(R.id.tipoP);

        Intent intent = getIntent();
        String name = intent.getStringExtra("nombre");
        String tipe = intent.getStringExtra("tipo");
        String url = intent.getStringExtra("imagen");

        nombre.setText(name);
        tipo.setText(tipe);
        Picasso.get().load(url).into(imagen);
    }
}
