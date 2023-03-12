package blackjack.domain.card

import blackjack.domain.state.Score

class Cards(private val cards: Set<Card> = setOf()) {
    val size: Int = cards.size

    val isContainsAce: Boolean = cards.any { it.isAce }

    constructor(vararg cards: Card) : this(cards.toSet())

    fun toList() = cards.toList()

    fun calculateScore(): Score = Score(
        cards.sumOf { it.value.value },
        isContainsAce,
    )

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
