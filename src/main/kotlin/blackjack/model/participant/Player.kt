package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.state.State

class Player(name: String, state: State, private val onDetermineHit: (String) -> Boolean) : Participant(name, state) {
    override fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    ) {
        if (onDetermineHit(name)) {
            state = State.Running(hand).hit(onDraw())
            onDone(this)
            play(onDraw, onDone)
        } else {
            state = State.Running(hand).stay()
            onDone(this)
        }
    }

    override fun judge(other: Participant): GameResult =
        when {
            isBust() -> GameResult.LOSE
            other.isBust() -> GameResult.WIN
            isBlackJack() && other.isBlackJack() -> GameResult.DRAW
            isBlackJack() -> GameResult.WIN
            other.isBlackJack() -> GameResult.LOSE
            else -> {
                val compared = sumScore() compareTo other.sumScore()
                GameResult.from(compared)
            }
        }
}
