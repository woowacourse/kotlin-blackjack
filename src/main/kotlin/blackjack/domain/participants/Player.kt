package blackjack.domain.participants

import blackjack.domain.card.Card

class Player(
    val name: String,
    initialHand: List<Card> = emptyList(),
) : Participant(initialHand) {
    override fun canHit(): Boolean = !isBust()

    override fun visibleCard(isFirstTurn: Boolean): List<Card> = hand.cards
}
