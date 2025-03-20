package blackjack.model.domain.participant

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return !hand.isBust()
    }

    override fun showStartCards(): List<Card> {
        return hand.cards
    }
}
