package com.example.pokedex;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import com.example.pokedex.R;
import com.example.pokedex.entities.PokemonDetail;
import com.example.pokedex.entities.TypeSlot;
import com.example.pokedex.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameText, typesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        nameText = findViewById(R.id.nameText);
        typesText = findViewById(R.id.typesText);

        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        nameText.setText(name.toUpperCase());

        String[] parts = url.split("/");
        int id = Integer.parseInt(parts[parts.length - 1]);
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
        Picasso.get().load(imageUrl).into(imageView);

        RetrofitClient.getInstance().getPokemonDetails(id).enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful()) {
                    StringBuilder types = new StringBuilder();
                    for (TypeSlot slot : response.body().getTypes()) {
                        types.append(slot.getType().getName()).append(", ");
                    }
                    typesText.setText("Tipos: " + types.toString().replaceAll(", $", ""));
                }
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                typesText.setText("Error al cargar tipos");
            }
        });
    }
}