package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.state.State

class Dealer(state: State) : Participant(DEALER_NAME, state) {
    override fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    ) {
        if (state.sumScore() < HIT_CONDITION) {
            state = State.Running(hand).hit(onDraw())
            onDone(this)
            play(onDraw, onDone)
        } else {
            state = State.Running(hand).stay()
            onDone(this)
        }
    }

    override fun judge(other: Participant): GameResult {
        if (other.ratePoint == State.BUST_RATE_POINT) return GameResult.WIN
        val compared = ratePoint compareTo other.ratePoint
        return GameResult.from(compared)
    }

    fun judge(other: List<Participant>): Map<GameResult, Int> {
        return other.map { judge(it) }
            .groupingBy { it }
            .eachCount()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_CONDITION = 17
    }
}
