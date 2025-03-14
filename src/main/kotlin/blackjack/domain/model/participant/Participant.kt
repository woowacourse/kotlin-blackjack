package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

abstract class Participant(
    val name: String,
    val hand: Hand,
) {
    fun drawCard(cards: List<Card>) {
        cards.forEach(hand::add)
    }

    fun handCards(): List<Card> = hand.toList()

    abstract fun showFirstHand(): List<Card>

    abstract fun compareTo(opponent: Participant): GameResult

    abstract fun isDrawable(): Boolean
}
