package model.human

import model.Hand

class Players private constructor(val players: List<Player>) {
    companion object {
        const val ERROR_EXCEED_PLAYERS = "플레이어의 수는 1 ~ 10 사이여야 합니다."
        private const val MIN_PLAYER_NUMBER: Int = 1
        private const val MAX_PLAYER_NUMBER: Int = 10
        private val NAME_LENGTH_RANGE: IntRange = MIN_PLAYER_NUMBER..MAX_PLAYER_NUMBER

        fun ofList(names: List<String>): Players {
            return names.validateLength().map { name ->
                Player(Hand(), HumanInfo(name))
            }.run {
                Players(this)
            }
        }

        private fun List<String>.validateLength(): List<String> {
            require(this.size in NAME_LENGTH_RANGE) { ERROR_EXCEED_PLAYERS }
            return this
        }
    }
}
