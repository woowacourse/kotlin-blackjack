package model.domain.state.gameinprogress

import model.domain.card.Card
import model.domain.card.Hand
import model.domain.state.State
import model.domain.state.gameover.BlackJack

class Dealing(override val hand: Hand) : GameInProgressState(hand) {

    override fun draw(card: Card): State {
        hand.drawOneCard(card)

        if (hand.cards.size < DEFAULT_HAND_CARD_SIZE) return Dealing(hand)
        if (hand.isBlackJack()) return BlackJack(hand)
        return Hit(hand)
    }

    companion object {
        private const val DEFAULT_HAND_CARD_SIZE = 2
    }
}
