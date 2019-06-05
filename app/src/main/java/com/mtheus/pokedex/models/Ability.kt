package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Ability {


    @SerializedName("ability")
    var ability: Ability2? = null

    @SerializedName("is_hidden")
    var is_hidden: Boolean? = null

    @SerializedName("slot")
    var slot: Int? = null



}