package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Hand

abstract class Participant(
    val name: String,
    val hand: Hand = Hand(),
) {
    fun drawCard(deck: Deck) {
        val card = deck.pop()
        hand.add(card)
    }

    abstract fun compareTo(opponent: Participant): GameResult

    abstract fun isDrawable(): Boolean
}
