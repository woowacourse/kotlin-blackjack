package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState

abstract class Participant(val name: String, val cardState: CardState) {
    abstract fun getFirstOpenCards(): List<Card>

    abstract fun draw(card: Card): Participant

    abstract fun stay(): Participant

    abstract fun getProfit(other: Participant): Money

    fun getCards(): List<Card> = cardState.getAllCards()

    fun getFirstCard(): Card = cardState.getFirstCard()

    fun canDraw(): Boolean = !cardState.isFinished

    fun getTotalScore(): Int = cardState.getTotalScore()
}
