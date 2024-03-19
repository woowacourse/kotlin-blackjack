package blackjack.model.game

import blackjack.model.user.Participant.Player

class GameRevenue(private val playersResult: Map<Player, Result>) {
    private var _playersRevenue: MutableList<Revenue> = mutableListOf()
    val playersRevenue: List<Revenue>
        get() = _playersRevenue.toList()

    init {
        calculatePlayersRevenue()
    }

    private fun calculatePlayersRevenue() {
        return playersResult.forEach { playerResult ->
            val revenue = Revenue()
            revenue.calculateRevenue(playerResult.key, playerResult.value)
            _playersRevenue.add(revenue)
        }
    }

    fun calculateDealerRevenue(): Revenue {
        return Revenue(
            playersRevenue.sumOf { playerRevenue ->
                -playerRevenue.amount
            },
        )
    }
}
