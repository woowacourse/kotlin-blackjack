package blackjack.state

import blackjack.model.Card
import blackjack.model.Hand

class Hit(private val hand: Hand = Hand()) : Running(hand) {
    override fun draw(card: Card): State {
        hand.add(card)
        val sumOfCard = hand.calculate()
        return when {
            sumOfCard > THRESHOLD_BUST -> Bust(hand)
            sumOfCard == THRESHOLD_BUST && hand.cards.size == 2 -> Blackjack(hand)
            sumOfCard == THRESHOLD_BUST -> Stay(hand)
            else -> Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)

    companion object {
        const val THRESHOLD_BUST = 21
    }
}
