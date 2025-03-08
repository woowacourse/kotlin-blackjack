package blackjack.domain.model.participant

import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards

abstract class Participant(
    val name: String,
    val handCards: HandCards = HandCards(),
) {
    fun drawCard(deck: Deck) {
        val card = deck.pop()
        handCards.add(card)
    }

    abstract fun isDrawable(): Boolean
}
