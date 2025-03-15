package model.participant

import model.card.Card
import model.card.Cards
import model.result.ProfitCalculator.Companion.BLACKJACK_SCORE
import model.result.ScoreCalculator

abstract class Participant(private val cards: Cards) {
    private val handCards: MutableList<Card>
        get() = cards.allCards

    val score: Int
        get() = ScoreCalculator(cards).calculateTotalCardScore()

    val isBlackJack: Boolean =
        handCards.size == INITIAL_CARDS_COUNT && ScoreCalculator(cards).initialTotalCardScore == BLACKJACK_SCORE

    val isBust: Boolean = score > BLACKJACK_SCORE

    abstract fun turn(drawnCard: Card): Boolean

    abstract fun canHit(): Boolean

    protected fun addCard(card: Card) {
        handCards.add(card)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
