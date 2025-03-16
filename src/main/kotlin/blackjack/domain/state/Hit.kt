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

    override fun canDrawCard(): Boolean = hand.getTotalScore() <= DRAW_CARD_STANDARD

    override fun profit(profitMoney: Int): Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val BLACKJACK_SIZE = 2
        const val BLACKJACK_SCORE = 21
        const val DRAW_CARD_STANDARD = 16
    }
}
