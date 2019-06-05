package com.mtheus.pokedex.infra.api.endPoints

import com.mtheus.pokedex.models.PokemonReceiver
import retrofit2.Call
import retrofit2.http.GET

interface PokemonReceiverEndPoint {


@GET("pokemon/")
    fun getPokemons():Call<PokemonReceiver>


}