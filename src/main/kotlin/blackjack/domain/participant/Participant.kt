package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult

abstract class Participant(val name: String) {
    private val cards = Cards()

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun canDraw(): Boolean

    fun getTotalScore(): Int = cards.calculateTotalScore()

    fun isBust(): Boolean = cards.isOverBlackjack()

    fun isStay(): Boolean = cards.isStay()

    private fun isBlackjack(): Boolean = cards.isBlackjack()

    infix fun judge(other: Participant): GameResult = when {
        isBust() -> when {
            this is Player -> GameResult.LOSE
            other.isBust() -> GameResult.WIN
            else -> GameResult.LOSE
        }

        isBlackjack() -> when (this) {
            is Player -> if (other.isBlackjack()) GameResult.DRAW else GameResult.BLACKJACK_WIN
            else -> if (other.isBlackjack()) GameResult.DRAW else GameResult.WIN
        }

        other.isBust() -> GameResult.WIN
        getTotalScore() == other.getTotalScore() -> GameResult.DRAW
        getTotalScore() > other.getTotalScore() -> GameResult.WIN
        else -> GameResult.LOSE
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards.items

    fun getFirstCard(): Card = cards.getFirstCard()
}
