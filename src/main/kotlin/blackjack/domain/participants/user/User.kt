package blackjack.domain.participants.user

import blackjack.domain.card.Card
import blackjack.domain.state.Score
import blackjack.domain.state.State

abstract class User(val name: Name, var state: State) {

    val score: Score
        get() = state.score

    abstract val isContinuable: Boolean

    fun draw(card: Card) {
        state = state.draw(card)
    }
}
