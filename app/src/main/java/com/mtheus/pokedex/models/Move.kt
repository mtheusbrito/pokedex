package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class Move {

    @SerializedName("move")
    var move: Move2? = null

    @SerializedName("version_group_details")
    var version_group_details: List<VersionGroupDetail>? = null

}