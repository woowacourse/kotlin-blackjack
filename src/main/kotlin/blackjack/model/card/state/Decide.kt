package blackjack.model.card.state

import blackjack.model.card.CardHand
import blackjack.model.result.Score

abstract class Decide(val cardHand: CardHand) : CardsState {
    override fun getCardHands(): CardHand = cardHand

    override fun countCards(): Int = cardHand.hand.size

    override fun getCardHandScore(): Score = cardHand.calculateScore()
}
