package com.mtheus.pokedex.infra.api

import com.mtheus.pokedex.infra.api.endPoints.PokemonReceiverEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    val BASE_URL = "https://pokeapi.co/"

    private lateinit var retrofit: Retrofit

    lateinit var pokemonReceiverEndPoint: PokemonReceiverEndPoint


    constructor() {
        val builderRetrofit = Retrofit.Builder()
        retrofit = builderRetrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        pokemonReceiverEndPoint = this.retrofit.create(PokemonReceiverEndPoint::class.java)

    }
}