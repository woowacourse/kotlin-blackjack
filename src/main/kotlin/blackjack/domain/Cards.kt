package blackjack.domain

class Cards(private val cards: MutableSet<Card> = mutableSetOf()) {
    val size: Int
        get() = cards.size

    fun toList() = cards.toList()

    fun add(card: Card) = cards.add(card)

    fun containsACE() = cards.map { it.value }.contains(CardValue.ACE)

    companion object {
        private val CARDS: List<Card> = CardMark.values().flatMap { mark ->
            CardValue.values().map { value -> Card(mark, value) }
        }

        fun all(): List<Card> = CARDS
    }
}
