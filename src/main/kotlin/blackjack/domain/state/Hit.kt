package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Hit(
    override val hand: Hand,
) : State {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.hasBust() -> Bust(hand)
            hand.hasBlackjack() -> Blackjack(hand)
            else -> this
        }
    }

    override fun canDrawCard(): Boolean = hand.getTotalScore() <= DEALER_DRAW_CARD_STANDARD

    override fun profit(state: State): Double = 0.0

    fun changeStay(): State = Stay(hand)

    companion object {
        const val DEALER_DRAW_CARD_STANDARD = 16
    }
}
