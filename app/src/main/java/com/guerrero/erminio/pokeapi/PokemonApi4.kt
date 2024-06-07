package com.guerrero.erminio.pokeapi

import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi4 {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>
}