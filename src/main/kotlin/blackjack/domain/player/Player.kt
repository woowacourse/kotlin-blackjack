package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    abstract fun canHit(): Boolean

    fun addCard(card: Card) {
        cards.addCard(card)
    }
}
