package blackjack.model

class Deck(cards: List<Card>) {
    var cards: List<Card> = cards.toList()
        private set

    constructor(size: Int = DEFAULT_DECK_SIZE) : this(create(size).cards)

    fun pull(): Card {
        val pulledCard = cards[0]
        cards = cards.subList(1, cards.size)
        return pulledCard
    }

    fun refillIfDeckEmpty(deck: Deck = create()) {
        if (cards.isEmpty()) {
            cards = deck.cards
        }
    }

    companion object {
        fun create(size: Int = DEFAULT_DECK_SIZE): Deck {
            require(size >= DEFAULT_DECK_SIZE)
            if (size == DEFAULT_DECK_SIZE) return DECK
            return Deck(
                List(size) { create().cards }.flatten().toMutableList(),
            )
        }

        private fun create(): Deck {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: MutableList<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }.shuffled().toMutableList()
            return Deck(deck)
        }

        private val DECK: Deck = create()
        private const val DEFAULT_DECK_SIZE = 1
    }
}
