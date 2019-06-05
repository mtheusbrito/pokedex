package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Type {
    @SerializedName("slot")
    var slot: Int? = null

    @SerializedName("type")
    var type: Type2? = null

}