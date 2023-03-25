package model.domain.state.gameinprogress

import model.domain.card.Card
import model.domain.card.Hand
import model.domain.state.State
import model.domain.state.gameover.Stay

abstract class GameInProgressState(override val hand: Hand) : State {
    abstract override fun draw(card: Card): State

    override fun stay(): State = Stay(hand)

    override fun getTotalScore(): Int = hand.getTotalScoreWithAceCard()
}
