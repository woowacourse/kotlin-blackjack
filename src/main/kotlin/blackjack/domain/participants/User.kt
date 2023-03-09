package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class User(val name: Name) {
    var cards = Cards()

    val score: Int
        get() = cards.result.score

    val isBlackJack: Boolean
        get() = cards.result.isBlackJackNumber

    val isBust: Boolean
        get() = cards.result.isOverBlackJackNumber

    abstract val isContinuable: Boolean

    fun draw(card: Card) { cards += card }
}
