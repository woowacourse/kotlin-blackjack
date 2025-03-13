package blackjack.domain.participants

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Dealer(
    private val deck: Deck,
    initialHand: List<Card> = emptyList(),
) : Participant(initialHand) {
    override fun canHit(): Boolean = score() <= GameRule.DEALER_ADDITIONAL_DRAW_BASE_SCORE

    fun handOut(participant: Participant) {
        val card = drawFromDeck()
        participant.addCard(card)
    }

    private fun drawFromDeck(): Card = deck.draw()
}
