package domain

import model.Name
import model.Participants

class GameResult(private val participants: Participants) {
    val dealerResult: Long
        get() = playersResult.values.filter { it < 0 }.sum() * -1
    val playersResult: Map<Name, Long>
        get() = getProfitResult()

    private fun getProfitResult(): Map<Name, Long> {
        return participants.players.getGameProfitMoney(participants.dealer)
    }
}
