package blackjack.domain.card

import blackjack.domain.state.Score

class Cards(private val cards: Set<Card> = setOf()) {
    val size: Int = cards.size

    val isContainsAce: Boolean = cards.any { it.isAce }

    private val hardScore: Score = Score(cards.toList().sumOf { it.value.value })

    private val softScore: Score = hardScore + SOFT_ACE_SCORE

    fun toList() = cards.toList()

    operator fun plus(card: Card): Cards {
        require(card !in cards) { ERROR_EXIST_DUPLICATE_CARD }
        return Cards(cards.plus(card))
    }

    fun calculateScore(): Score = when {
        isContainsAce && softScore.isBust.not() -> softScore
        else -> hardScore
    }

    companion object {
        private const val ERROR_EXIST_DUPLICATE_CARD = "카드는 중복될 수 없습니다."
        private val SOFT_ACE_SCORE = Score(10)
        private val CARDS: List<Card> = CardMark.values().flatMap { mark ->
            CardValue.values().map { value -> Card(mark, value) }
        }

        fun all(): List<Card> = CARDS
    }
}
