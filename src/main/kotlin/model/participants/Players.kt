package model.participants

import model.ParticipantState

class Players private constructor(val players: List<Player>) {
    companion object {
        const val ERROR_EXCEED_PLAYERS = "플레이어의 수는 1 ~ 10 사이여야 합니다."

        fun ofList(names: List<String>): Players {
            return names.validateLength().map {
                Player(ParticipantState.Playing(Hand()), ParticipantName.fromInput(it))
            }.run {
                Players(this)
            }
        }

        private fun List<String>.validateLength(): List<String> {
            require(this.size in 1..10) { ERROR_EXCEED_PLAYERS }
            return this
        }
    }
}
