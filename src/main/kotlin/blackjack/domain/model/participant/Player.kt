package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return !hand.isBust()
    }

    override fun getInitCard(): List<Card> {
        return hand.cards.take(2)
    }
}
