package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

@Suppress("FunctionName")
fun <T : Participant> BlackJackRole(participant: T): BlackJackRole<T> {
    return when {
        participant.isBust() -> Bust(participant)
        participant.isBlackJack() -> BlackJack(participant)
        else -> Stay(participant)
    }
}

interface BlackJackRole<T : Participant> {
    val participant: T

    fun getEarnRate(gameResult: GameResult): Double

    fun compare(blackJackRole: BlackJackRole<out Participant>): GameResult
}
