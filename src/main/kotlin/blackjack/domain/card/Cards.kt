package blackjack.domain.card

class Cards(private val cards: Set<Card> = setOf()) {
    val size: Int
        get() = cards.size

    fun toList() = cards.toList()

    operator fun plus(card: Card): Cards = Cards(cards.plus(card))

    fun containsACE() = cards.map { it.value }.contains(CardValue.ACE)

    companion object {
        private val CARDS: List<Card> = CardMark.values().flatMap { mark ->
            CardValue.values().map { value -> Card(mark, value) }
        }

        fun all(): List<Card> = CARDS
    }
}
