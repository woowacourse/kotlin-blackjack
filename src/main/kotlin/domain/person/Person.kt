package domain.person

import domain.card.Card
import domain.card.Hand
import domain.money.Money
import domain.result.Score
import domain.state.Finished
import domain.state.Started
import domain.state.State

abstract class Person(val name: String) {

    abstract var state: State

    val score: Score
        get() = Score.of(Hand(state.getHandCards()))

    fun isFinished() = state is Finished

    fun isStarted() = state is Started

    fun receiveCard(cards: List<Card>) {
        cards.forEach { state = state.draw(it) }
    }

    fun stay() {
        state = state.stay()
    }

    fun getHandCards() = state.getHandCards()

    fun getPlayerProfit(other: State, money: Money) = state.profit(other, money)
}
