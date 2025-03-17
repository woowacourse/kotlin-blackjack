package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

sealed class Hittable : ParticipantState() {
    abstract fun hit(card: Card): ParticipantState
}

class Ready(
    val card: Card,
) : Hittable() {
    override val hand: Hand = Hand(card)

    override fun hit(card: Card): ParticipantState {
        hand.draw(card)
        return when {
            score.isBlackjackScore -> Blackjack(hand)
            else -> Playing(hand)
        }
    }
}

class Playing(
    override val hand: Hand,
) : Hittable() {
    override fun hit(card: Card): ParticipantState {
        hand.draw(card)
        return when {
            score.isBustedScore -> Busted(hand)
            score.isBlackjackScore -> Stay(hand)
            else -> Playing(hand)
        }
    }

    fun stay(): ParticipantState = Stay(hand)
}
