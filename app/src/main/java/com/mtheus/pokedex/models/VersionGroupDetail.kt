package com.mtheus.pokedex.models

import com.google.gson.annotations.SerializedName

class VersionGroupDetail {

    @SerializedName("level_learned_at")
    var level_learned_at: Int? = null

    @SerializedName("move_learn_method")
    var move_learn_method: MoveLearnMethod? = null

    @SerializedName("version_group")
    var version_group: VersionGroup? = null

}