package blackjack.model.user

import blackjack.model.card.Card
import blackjack.model.game.Score

class Hand {
    private val _cards: MutableSet<Card> = mutableSetOf()
    val cards: Set<Card>
        get() = _cards.toSet()

    var score: Score = Score(_cards)
        private set

    fun drawCard(card: Card) {
        _cards.add(card)
        updateScore()
    }

    private fun updateScore() {
        score = Score(_cards)
    }
}
