package blackjack.model.card.state

import blackjack.model.card.CardHand

class BlackJack(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double {
        if (other is BlackJack) return 0.0
        return BLACK_JACK_EARNING_RATE
    }

    companion object {
        private const val BLACK_JACK_EARNING_RATE = 1.5
    }
}
