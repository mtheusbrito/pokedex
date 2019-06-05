package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Sprites {
    @SerializedName("back_default")
    var back_default: String? = null

    @SerializedName("back_female")
    var back_female: Any? = null

    @SerializedName("back_shiny")
    var back_shiny: String? = null

    @SerializedName("back_shiny_female")
    var back_shiny_female: Any? = null

    @SerializedName("front_default")
    var front_default: String? = null

    @SerializedName("front_female")
    var front_female: Any? = null

    @SerializedName("front_shiny")
    var front_shiny: String? = null

    @SerializedName("front_shiny_female")
    var front_shiny_female: Any? = null

}