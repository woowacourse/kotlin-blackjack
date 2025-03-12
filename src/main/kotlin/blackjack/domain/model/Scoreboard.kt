package blackjack.domain.model

import blackjack.domain.model.GameResult.BLACKJACK_WIN
import blackjack.domain.model.GameResult.DRAW
import blackjack.domain.model.GameResult.LOSE
import blackjack.domain.model.GameResult.WIN
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class Scoreboard(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun getDealerProfit(): Double {
        val playersProfit: Collection<Double> = getPlayersProfit().values
        return -playersProfit.filter { it < 0.0 }.sum() - playersProfit.filter { it > 0.0 }.sum()
    }

    fun getPlayersProfit(): Map<Player, Double> {
        return players.associateWith(::getProfit)
    }

    private fun getProfit(player: Player): Double {
        val betAmount: Double = player.betAmount.value
        return when (player.compareTo(dealer)) {
            BLACKJACK_WIN -> betAmount * 1.5
            WIN -> betAmount
            DRAW -> 0.0
            LOSE -> -betAmount
        }
    }

    fun getDealerResult(): Map<GameResult, Int> {
        val dealerResult: List<GameResult> = players.map { player -> dealer.compareTo(player) }
        val initResult: Map<GameResult, Int> = GameResult.entries.associateWith { 0 }
        return initResult + dealerResult.groupingBy { it }.eachCount()
    }
}
