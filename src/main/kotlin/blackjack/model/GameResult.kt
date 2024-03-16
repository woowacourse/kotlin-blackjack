package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.Revenue.DealerRevenue
import blackjack.model.Revenue.PlayerRevenue

class GameResult(val dealer: Dealer, val players: List<Player>) {
    private val _playersResult: MutableList<Result> = mutableListOf()
    val playersResult: List<Result>
        get() = _playersResult.toList()

    private val _playersRevenue: MutableList<PlayerRevenue> = mutableListOf()
    val playersRevenue: List<PlayerRevenue>
        get() = _playersRevenue.toList()

    init {
        judgePlayersResult()
        calculateParticipantsRevenue()
    }

    fun calculateDealerRevenue(): DealerRevenue {
        return DealerRevenue(playersRevenue)
    }

    private fun judgePlayersResult() {
        players.forEach { player ->
            _playersResult.add(Result.judgeResult(dealer, player))
        }
    }

    private fun calculateParticipantsRevenue() {
        playersResult.withIndex().forEach { (index, playerResult) ->
            val playerRevenue = PlayerRevenue(players[index], playerResult)
            _playersRevenue.add(playerRevenue)
        }
    }
}
