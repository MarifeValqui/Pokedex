package com.example.pokedex.service;


import com.example.pokedex.entities.PokemonDetail;
import com.example.pokedex.entities.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface PokeApiService {
    @GET ("pokemon")
    Call<PokemonResponse> getPokemonList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonDetail> getPokemonDetails(@Path("id") int id);
}
