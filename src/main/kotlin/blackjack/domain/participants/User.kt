package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class User(name: String) {
    val name = Name(name)
    var cards = Cards()

    fun getScore(): Int = cards.result.score()

    fun isBlackJack(): Boolean = cards.result.score() == BLACKJACK_NUMBER

    abstract val isContinue: Boolean

    fun draw(card: Card) { cards += card }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
