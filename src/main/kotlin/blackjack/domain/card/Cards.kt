package blackjack.domain.card

import blackjack.domain.participants.Score

class Cards(private val cards: Set<Card> = setOf()) {
    val result = Score(this)

    val size: Int = cards.size

    val isContainsAce: Boolean
        get() = cards.any { it.isAce }

    fun toList() = cards.toList()

    operator fun plus(card: Card): Cards {
        require(card !in cards) { ERROR_EXIST_DUPLICATE_CARD }
        return Cards(cards.plus(card))
    }

    companion object {
        private const val ERROR_EXIST_DUPLICATE_CARD = "카드는 중복될 수 없습니다."
        private val CARDS: List<Card> = CardMark.values().flatMap { mark ->
            CardValue.values().map { value -> Card(mark, value) }
        }

        fun all(): List<Card> = CARDS
    }
}
