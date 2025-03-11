package blackjack.domain.person

import blackjack.domain.Score
import blackjack.domain.card.Card

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    private val _score: Score = Score(cards)
    val score: Int get() = _score.value

    fun addCard(card: Card) {
        _cards.add(card)
        _score.update(cards)
    }
}
