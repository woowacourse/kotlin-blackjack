package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Dealer(
    private val deck: Deck,
    initialHand: List<Card> = emptyList(),
) : Participant(initialHand) {
    override fun canHit(): Boolean = score() <= DRAW_SCORE

    fun handOut(participant: Participant) {
        val card = drawFromDeck()
        participant.addCard(card)
    }

    private fun drawFromDeck(): Card = deck.draw()

    companion object {
        private const val DRAW_SCORE = 16
    }
}
