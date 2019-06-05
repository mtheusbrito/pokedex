package com.mtheus.pokedex.infra.api.endPoints

import com.mtheus.pokedex.models.Pokemon
import com.mtheus.pokedex.models.PokemonReceiver
import com.mtheus.pokedex.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonReceiverEndPoint {


    @GET("api/v2/pokemon/")
    fun getPokemons(): Call<PokemonReceiver>

    @GET("api/v2/pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>


}