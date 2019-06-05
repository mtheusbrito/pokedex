package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class PokemonReceiver {


    @SerializedName("count")
    var count: Int? = null

    @SerializedName("next")
    var next: Any? = null

    @SerializedName("previous")
    var previous: Any? = null

    @SerializedName("results")
    var results: List<Result>? = null



}