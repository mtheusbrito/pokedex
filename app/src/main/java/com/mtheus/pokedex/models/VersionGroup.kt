package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class VersionGroup {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}