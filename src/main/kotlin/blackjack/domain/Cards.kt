package blackjack.domain

class Cards(private val cards: MutableSet<Card> = mutableSetOf()) {
    val size: Int
        get() = cards.size

    fun toList() = cards.toList()

    fun add(card: Card) = cards.add(card)

    fun containsACE() = cards.map { it.value }.contains(CardValue.ACE)
}
