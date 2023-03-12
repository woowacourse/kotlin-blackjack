package domain

import domain.card.Card
import state.BustState
import state.FirstState
import state.State

abstract class Participant(val name: Name) {
    var cardsState: State = FirstState()
        private set

    abstract fun isPossibleDrawCard(): Boolean
    fun addCard(card: Card) {
        cardsState = cardsState.draw(card)
    }

    fun stay() {
        cardsState = cardsState.stay()
    }

    fun getScore(): Score = cardsState.score
    fun isBust(): Boolean {
        if (cardsState is BustState) return true
        return false
    }

    fun getCards(): List<Card> = cardsState.getCards()
}
