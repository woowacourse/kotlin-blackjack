package blackjack.model.card

class Deck(
    cards: List<Card>,
) {
    private val cards = cards.toMutableList()

    fun draw(): Card {
        if (cards.isEmpty()) cards.addAll(create().cards)
        return cards.removeLast()
    }

    fun drawMultiple(size: Int): List<Card> {
        return List(size) { draw() }
    }

    companion object {
        @JvmStatic
        fun create(): Deck {
            val suits = Suit.entries
            val ranks = Rank.entries
            val cards =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                        .shuffled()
                }.toMutableList()
            return Deck(cards)
        }
    }
}
