package blackjack.domain.card

import blackjack.domain.participants.Score

class Cards(val cards: Set<Card> = setOf()) {
    val result = Score(this)

    val size: Int
        get() = cards.size

    operator fun plus(card: Card): Cards = Cards(cards.plus(card))

    fun containsACE() = cards.map { it.value }.contains(CardValue.ACE)

    companion object {
        private val CARDS: List<Card> = CardMark.values().flatMap { mark ->
            CardValue.values().map { value -> Card(mark, value) }
        }

        fun all(): List<Card> = CARDS
    }
}
