package blackjack.model.card.state

import blackjack.model.card.CardHand

class Bust(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double = -1.0
}
