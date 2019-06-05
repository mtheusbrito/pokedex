package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Pokemon {

    @SerializedName("abilities")
    var abilities: List<Ability>? = null

    @SerializedName("base_experience")
    var base_experience: Int? = null

    @SerializedName("forms")
    var forms: List<Form>? = null

    @SerializedName("game_indices")
    var game_indices: GameIndice? = null

    @SerializedName("height")
    var height: Int? = null
    @SerializedName("held_items")
    var held_items: List<Any>? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("is_default")
    var is_default: Boolean? = null

    @SerializedName("location_area_encounters")
    var location_area_encounters: String? = null

    @SerializedName("moves")
    var moves: List<Move>? = null

    @SerializedName("order")
    var order: Int? = null

    @SerializedName("species")
    var species: Species? = null

    @SerializedName("sprites")
    var sprites: Sprites? = null

    @SerializedName("stats")
    var stats: List<Stat>? = null

    @SerializedName("types")
    var types: List<Type>? = null

    @SerializedName("weight")
    var weight: Int? = null

}