package domain

import domain.participants.Dealer
import domain.participants.Player
import domain.result.ParticipantsResult
import domain.result.PlayerResult

class BlackjackResult(private val dealer: Dealer, private val players: List<Player>) {

    fun getResult() = ParticipantsResult(dealer, getPlayerResult())

    private fun getPlayerResult(): List<PlayerResult> {
        return players.map {
            PlayerResult(
                it,
                calculatePlayerProfit(it)
            )
        }
    }

    private fun calculatePlayerProfit(player: Player): Int {
        if (player.ownCards.checkBlackJack())
            return player.bettingMoney * BLACKJACK_PROFIT_RATE
        return player.getWinningResult(dealer).calculateProfit(player.bettingMoney)
    }

    companion object {
        private const val BLACKJACK_PROFIT_RATE = 1.5
    }
}
