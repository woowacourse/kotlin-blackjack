package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class User(name: String) {
    val name = Name(name)
    var cards = Cards()

    val score: Int
        get() = cards.result.score()

    val isBlackJack: Boolean
        get() = cards.result.isBlackJackNumber

    val isBust: Boolean
        get() = cards.result.isOverBlackJackNumber

    abstract val isContinue: Boolean

    fun draw(card: Card) { cards += card }
}
