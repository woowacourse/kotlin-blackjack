package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

@Suppress("FunctionName")
fun <T : Participant> State(participant: T): State<T> {
    return when {
        participant.isBust() -> Bust(participant)
        participant.isBlackJack() -> BlackJack(participant)
        else -> Stay(participant)
    }
}

interface State<T : Participant> {
    val participant: T

    fun getEarnRate(gameResult: GameResult): Double

    fun compare(state: State<out Participant>): GameResult
}
