package blackjack.domain.update.state

import blackjack.domain.Card
import blackjack.domain.update.NewHand

sealed class Hittable : NewParticipantState() {
    abstract fun hit(card: Card): NewParticipantState
}

class Ready(
    val card: Card,
) : Hittable() {
    override val hand: NewHand = NewHand(card)

    override fun hit(card: Card): NewParticipantState {
        hand.draw(card)
        return when {
            score.isBlackjackScore -> Blackjack(hand)
            else -> Playing(hand)
        }
    }
}

class Playing(
    override val hand: NewHand,
) : Hittable() {
    override fun hit(card: Card): NewParticipantState {
        hand.draw(card)
        return when {
            score.isBustedScore -> Busted(hand)
            score.isBlackjackScore -> Stay(hand)
            else -> Playing(hand)
        }
    }

    fun stay(): NewParticipantState = Stay(hand)
}
