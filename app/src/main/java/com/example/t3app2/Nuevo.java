package com.example.t3app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.t3app2.entities.Pokemon;
import com.example.t3app2.services.PokemonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nuevo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        EditText nombre = findViewById(R.id.name);
        EditText tipo = findViewById(R.id.tipe);
        EditText imagen = findViewById(R.id.image);
        EditText latitud = findViewById(R.id.latitud);
        EditText longitud = findViewById(R.id.longitud);
        Button crear = findViewById(R.id.newPokemon);

        crear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = nombre.getText().toString().trim();
                String tipos = tipo.getText().toString().trim();
                String image = imagen.getText().toString().trim();
                float latitude = Float.parseFloat(latitud.getText().toString().trim());
                float longitude = Float.parseFloat(longitud.getText().toString().trim());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokemonService service = retrofit.create(PokemonService.class);

                Pokemon pokemon = new Pokemon();
                pokemon.setNombre(name);
                pokemon.setTipo(tipos);
                pokemon.setUrl_imagen(image);
                pokemon.setLatitude(latitude);
                pokemon.setLongitude(longitude);

                Call<Pokemon> call = service.create(pokemon);

                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                    }
                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                    }
                });
                Intent intent = new Intent(Nuevo.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}