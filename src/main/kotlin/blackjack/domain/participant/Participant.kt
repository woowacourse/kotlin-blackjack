package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.result.GameResult
import blackjack.domain.state.BlackjackState
import blackjack.domain.state.BustState
import blackjack.domain.state.CardState
import blackjack.domain.state.StayState

abstract class Participant(val name: String, val cardState: CardState) {
    abstract fun getFirstOpenCards(): List<Card>

    abstract fun draw(card: Card): Participant

    abstract fun stay(): Participant

    abstract fun getProfit(other: Participant): Money

    fun getCards(): List<Card> = cardState.getAllCards()

    fun getFirstCard(): Card = cardState.getFirstCard()

    fun canDraw(): Boolean = !cardState.isFinished

    fun getTotalScore(): Int = cardState.getTotalScore()

    fun isStay(): Boolean = cardState is StayState

    fun isBust(): Boolean = cardState is BustState

    fun isBlackjack(): Boolean = cardState is BlackjackState

    fun compareScore(other: Participant): GameResult {
        val scoreGap = cardState.getTotalScore() - other.cardState.getTotalScore()
        return GameResult.getWinLoseDraw(scoreGap)
    }
}
