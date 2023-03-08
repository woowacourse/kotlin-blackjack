package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.Score.Companion.BLACKJACK_NUMBER

abstract class User(name: String) {
    val name = Name(name)
    var cards = Cards()

    val score: Int
        get() = cards.result.score()

    val isBlackJack: Boolean
        get() = score == BLACKJACK_NUMBER

    val isBust: Boolean
        get() = score > BLACKJACK_NUMBER

    abstract val isContinue: Boolean

    fun draw(card: Card) { cards += card }
}
