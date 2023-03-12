package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState

abstract class Participant(val name: String, val cardState: CardState) {
    abstract val maxDrawableScore: Int

    val isRunning: Boolean = cardState.isRunning

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun draw(card: Card, isFirstDraw: Boolean = false): Participant

    abstract fun stay(): Participant

    abstract fun getProfit(other: Participant): Money

    abstract fun canDraw(): Boolean

    fun getCards(): List<Card> = cardState.getAllCards()

    fun getFirstCard(): Card = cardState.getFirstCard()

    fun getTotalScore(): Int = cardState.getTotalScore()
}
