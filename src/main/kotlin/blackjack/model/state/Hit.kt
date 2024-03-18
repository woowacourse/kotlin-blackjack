package blackjack.model.state

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card

data class Hit(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        val hand = Hand(hand.cards + card, hand.profit)

        if (hand.size == 2 && hand.calculateTotal() == 21) {
            return BlackJack(hand)
        }
        if (hand.calculateTotal() > 21) {
            return Bust(hand)
        }

        return Hit(hand)
    }

    override fun profit(dealerTotal: Int): Int {
        return hand.calculateProfit(hand.findWinner(dealerTotal).toDouble()).toInt()
    }
}
