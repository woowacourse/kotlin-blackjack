package blackjack.domain

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards.toList()

    fun sum(): Int {
        return cards.sumOf { it.getScore() }
    }

    fun count(): Int {
        return cards.count { it.rank == Rank.ACE }
    }

    fun size(): Int {
        return this.size()
    }
}
