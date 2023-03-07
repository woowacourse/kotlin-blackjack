package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.Score.Companion.BLACKJACK_NUMBER

abstract class User(name: String) {
    val name = Name(name)
    var cards = Cards()

    fun score(): Int = cards.result.score()

    fun isBlackJack(): Boolean = score() == BLACKJACK_NUMBER

    fun isBust(): Boolean = score() > BLACKJACK_NUMBER

    fun draw(card: Card) { cards += card }

    abstract fun isContinue(): Boolean
}
