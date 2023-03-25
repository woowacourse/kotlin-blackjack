package model.domain.player

import model.domain.card.Deck
import model.domain.state.State
import model.tools.Money

abstract class Player(val name: String) {

    abstract var state: State
    var money: Money = Money.from(EMPTY)
    abstract fun betMoney(bettingMoney: Money)
    fun draw(deck: Deck) {
        state = state.draw(deck.getCard())
    }

    fun stay() {
        state = state.stay()
    }

    fun getTotalScore(): Int = state.getTotalScore()

    companion object {
        private const val EMPTY = 0
    }
}
