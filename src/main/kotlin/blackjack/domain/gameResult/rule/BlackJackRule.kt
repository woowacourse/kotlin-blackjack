package blackjack.domain.gameResult.rule

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

@Suppress("FunctionName")
fun <T : Participant> BlackJackRule(participant: T): BlackJackRule<T> {
    return when {
        participant.isBust() -> Bust(participant)
        participant.isBlackJack() -> BlackJack(participant)
        else -> Stay(participant)
    }
}

sealed interface BlackJackRule<T : Participant> {
    val participant: T

    fun getEarnRate(gameResult: GameResult): Double

    fun compare(blackJackRule: BlackJackRule<out Participant>): GameResult
}
