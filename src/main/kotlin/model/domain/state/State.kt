package model.domain.state

import model.domain.card.Card
import model.domain.card.Hand

interface State {
    val hand: Hand
    fun draw(card: Card): State
    fun stay(): State
    fun getTotalScore(): Int
}
