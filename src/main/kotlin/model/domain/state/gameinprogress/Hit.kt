package model.domain.state.gameinprogress

import model.domain.card.Card
import model.domain.card.Hand
import model.domain.state.State
import model.domain.state.gameover.Bust

class Hit(override val hand: Hand) : GameInProgressState(hand) {
    override fun draw(card: Card): State {
        hand.drawOneCard(card)

        if (hand.isBust()) return Bust(hand)
        return Hit(hand)
    }
}
