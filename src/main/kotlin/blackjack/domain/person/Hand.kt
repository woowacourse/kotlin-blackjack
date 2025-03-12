package blackjack.domain.person

import blackjack.domain.Score
import blackjack.domain.card.Card

class Hand {
    private val cards: PersonCards = PersonCards()
    private val score: Score = Score(cards())

    fun addCard(card: Card) {
        cards.add(card)
        score.update(cards())
    }

    fun cards(): List<Card> {
        return cards.list()
    }

    fun score(): Int {
        return score.value
    }
}
