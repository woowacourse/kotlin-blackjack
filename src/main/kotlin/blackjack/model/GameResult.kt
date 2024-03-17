package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.Revenue.DealerRevenue
import blackjack.model.Revenue.PlayerRevenue

class GameResult(val dealer: Dealer, val players: List<Player>) {
    private val playersResult: List<Result> = judgePlayersResult()
    val playersRevenue: List<PlayerRevenue> = calculateParticipantsRevenue()

    fun calculateDealerRevenue(): DealerRevenue {
        return DealerRevenue(playersRevenue)
    }

    private fun judgePlayersResult(): List<Result> {
        return players.map { player ->
            Result.judgeResult(dealer, player)
        }
    }

    private fun calculateParticipantsRevenue(): List<PlayerRevenue> {
        return playersResult.withIndex().map { (index, playerResult) ->
            val playerRevenue = PlayerRevenue(players[index], playerResult)
            playerRevenue
        }
    }
}
