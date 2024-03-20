package blackjack.model.card.state

import blackjack.model.card.CardHand

class Bust(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double = BUST_EARNING_RATE

    companion object {
        private const val BUST_EARNING_RATE = -1.0
    }
}
