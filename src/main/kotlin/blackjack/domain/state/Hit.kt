package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Hit(
    override val hand: Hand,
) : State {
    override fun draw(card: Card): State {
        val hand = Hand(hand.cards + card, hand.money)
        if (hand.cards.size == BLACKJACK_SIZE && hand.getTotalScore() == BLACKJACK_SCORE) {
            return Blackjack(hand)
        }
        if (hand.getTotalScore() > BLACKJACK_SCORE) {
            return Bust(hand)
        }

        return Hit(hand)
    }

    override fun canDrawCard(): Boolean = hand.getTotalScore() <= DEALER_DRAW_CARD_STANDARD

    override fun profit(dealerTotal: Int): Int = hand.getProfitMoney(hand.findWinner(dealerTotal).toDouble()).toInt()

    companion object {
        const val BLACKJACK_SIZE = 2
        const val BLACKJACK_SCORE = 21
        const val DEALER_DRAW_CARD_STANDARD = 16
    }
}
