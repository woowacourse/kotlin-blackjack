package model

import model.human.HumanInfo

data class PlayersResult(private val _humanInfoList: MutableList<HumanInfo> = mutableListOf()) {
    val humanInfoList: List<HumanInfo>
        get() = _humanInfoList.toList()

    fun add(humanInfo: HumanInfo) {
        _humanInfoList.add(humanInfo)
    }
}
