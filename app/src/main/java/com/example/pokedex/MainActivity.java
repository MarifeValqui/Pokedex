package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokedex.R;
import com.example.pokedex.adapter.PokemonAdapter;
import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.entities.PokemonResponse;
import com.example.pokedex.service.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitClient.getInstance().getPokemonList(40, 20).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    List<Pokemon> list = response.body().getResults();
                    adapter = new PokemonAdapter(list, item -> {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("name", item.getName());
                        intent.putExtra("url", item.getUrl());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}