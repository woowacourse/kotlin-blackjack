package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState

abstract class Participant(val name: String, val cardState: CardState) {
    val isRunning: Boolean = cardState.isRunning

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun draw(card: Card, justDraw: Boolean = false): Participant

    abstract fun stay(): Participant

    abstract fun getProfit(others: List<Participant>): Money

    abstract fun canDraw(): Boolean

    fun getCards(): List<Card> = cardState.getAllCards()

    fun getFirstCard(): Card = cardState.getFirstCard()

    fun getTotalScore(): Int = cardState.getTotalScore()
}