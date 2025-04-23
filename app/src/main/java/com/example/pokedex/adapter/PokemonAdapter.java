package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokedex.R;
import com.example.pokedex.entities.Pokemon;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private List<Pokemon> pokemonList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Pokemon item);
    }

    public PokemonAdapter(List<Pokemon> list, OnItemClickListener listener) {
        this.pokemonList = list;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.pokemonName);
        }

        public void bind(final Pokemon item, final OnItemClickListener listener) {
            name.setText(item.getName().toUpperCase());
            itemView.setOnClickListener(view -> listener.onItemClick(item));
        }
    }

    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(pokemonList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
