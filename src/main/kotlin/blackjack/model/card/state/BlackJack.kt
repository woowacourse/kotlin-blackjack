package blackjack.model.card.state

import blackjack.model.card.CardHand

class BlackJack(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double {
        if (other is BlackJack) return 0.0
        return 1.5
    }
}
