package blackjack.model.game

import blackjack.model.game.Revenue.DealerRevenue
import blackjack.model.game.Revenue.PlayerRevenue
import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player

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
