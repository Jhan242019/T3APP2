package com.example.t3app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.t3app2.adapters.PokemonAdapter;
import com.example.t3app2.entities.Pokemon;
import com.example.t3app2.services.PokemonService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class T3_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3);
        RecyclerView rv = findViewById(R.id.rvPokemons);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);
        Call<List<Pokemon>> pokemon = service.getAll();

        pokemon.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if(response.code() == 200){
                    List<Pokemon> pokemones  = response.body();
                    PokemonAdapter adapter = new PokemonAdapter(pokemones);
                    adapter.OnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),
                                    "Selecciono pokemon " + pokemones
                                            .get(rv.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(T3_activity.this, Detalle.class);
                            String nombre = String.valueOf(pokemones.get(rv.getChildAdapterPosition(v)).getNombre());
                            String tipo = String.valueOf(pokemones.get(rv.getChildAdapterPosition(v)).getTipo());
                            String imagen = String.valueOf(pokemones.get(rv.getChildAdapterPosition(v)).getUrl_imagen());
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("tipo",tipo);
                            intent.putExtra("imagen",imagen);
                            startActivity(intent);
                        }
                    });
                    rv.setAdapter(adapter);
                }else {
                    Log.i("Web","Conexion fallida");
                }
            }
            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.i("Web","Error del servidor");
            }
        });
    }
}