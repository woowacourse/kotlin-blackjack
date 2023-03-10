package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.state.State

abstract class User(val name: Name, var state: State) {

    val score: Int
        get() = state.score.value

    val isBlackJack: Boolean
        get() = state.score.isBlackJack

    val isBust: Boolean
        get() = state.score.isBust

    abstract val isContinuable: Boolean

    fun draw(card: Card) {
        state = state.draw(card)
    }
}
