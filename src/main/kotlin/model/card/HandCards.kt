package model.card

import model.result.ProfitCalculator.Companion.BLACKJACK_SCORE
import model.result.ScoreCalculator

class HandCards(cards: Cards) {
    private val allCards: MutableList<Card> = cards.allCards

    val score: Int = ScoreCalculator(cards).calculateTotalCardScore()

    val isBlackJack: Boolean =
        allCards.size == INITIAL_CARDS_COUNT && ScoreCalculator(cards).initialTotalCardScore == BLACKJACK_SCORE

    val isBust: Boolean = score > BLACKJACK_SCORE

    fun addCard(card: Card) {
        allCards.add(card)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
