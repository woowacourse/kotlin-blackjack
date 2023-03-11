package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult
import blackjack.domain.state.CardState

abstract class Participant(val name: String, val cardState: CardState) {
    private val cards = Cards()

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun stay(): Participant

    fun canDraw(): Boolean = !cardState.isFinished

    fun getTotalScore(): Int = cardState.getTotalScore()

    fun isBust(): Boolean = cards.isOverBlackjack()

    fun isStay(): Boolean = cards.isStay()

    infix fun judge(other: Participant): GameResult {
        val myScore = getTotalScore()
        val otherScore = other.getTotalScore()

        return when {
            isBust() && other.isBust() -> GameResult.DRAW
            isBust() -> GameResult.LOSE
            other.isBust() || (myScore > otherScore) -> GameResult.WIN
            myScore == otherScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    abstract fun draw(card: Card): Participant

    fun getCards(): List<Card> = cardState.getAllCards()

    fun getFirstCard(): Card = cardState.getFirstCard()
}
