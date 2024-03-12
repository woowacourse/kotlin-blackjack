package blackjack.model.card.state

import blackjack.model.card.CardHand

abstract class Decide(val cardHand: CardHand) : CardsState {
    override fun getCardHands(): CardHand = cardHand

    override fun countCards(): Int = cardHand.hand.size
}
