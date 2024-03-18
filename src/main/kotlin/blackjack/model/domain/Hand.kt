package blackjack.model.domain

import blackjack.model.entitiy.Card

data class Hand(
    val cards: Set<Card>,
    val profit: Int,
) {
    val size = cards.size

    fun calculateProfit(rate: Double): Double {
        return rate * profit
    }

    fun findWinner(dealerTotal: Int): Int {
        return this.calculateTotal().compareTo(dealerTotal)
    }

    fun calculateTotal(): Int {
        val total = cards.sumOf { it.cardRank.value }

        return if (total <= 11 && cards.any { it.cardRank.symbol == "A" }) {
            total + 10
        } else {
            total
        }
    }
}
