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
                it.getWinningResult(dealer).calculateProfit(it.bettingMoney)
            )
        }
    }
}
