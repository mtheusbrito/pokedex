package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Stat {

    @SerializedName("base_stat")
    var base_stat: Int? = null

    @SerializedName("effort")
    var effort: Int? = null

    @SerializedName("stat")
    var stat: Stat2? = null
}