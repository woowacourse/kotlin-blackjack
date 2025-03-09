package blackjack.model

import blackjack.model.card.Card

object ScoreCalculator {
    fun sum(cards: List<Card>) = cards.sumOf { card -> card.number.score }
}
