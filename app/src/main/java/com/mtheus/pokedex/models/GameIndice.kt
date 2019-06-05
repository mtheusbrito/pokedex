package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class GameIndice {

    @SerializedName("game_index")
    var game_index: Int? = null

    @SerializedName("version")
    var version: Version? = null
}