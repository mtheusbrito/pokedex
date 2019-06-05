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
    var results: MutableList<Result>? = null


    override fun toString(): String {
        return next.toString()
    }



}