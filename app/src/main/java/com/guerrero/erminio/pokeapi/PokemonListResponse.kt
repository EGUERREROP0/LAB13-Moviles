package com.guerrero.erminio.pokeapi

data class PokemonListResponse(

    val count: Int,
    val next: String,

    val results: List<PokemonResponse>
)
