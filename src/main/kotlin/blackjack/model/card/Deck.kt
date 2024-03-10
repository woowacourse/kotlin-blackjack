package blackjack.model.card

class Deck(private val shuffleStrategy: (MutableList<Card>) -> Unit = { it.shuffle() }) {
    val cards: MutableList<Card> = mutableListOf()

    init {
        initialize()
    }

    fun initialize() {
        cards.clear()
        cards.addAll(
            Suit.entries.flatMap { suit -> Denomination.entries.map { denomination -> Card(denomination, suit) } },
        )
        shuffleStrategy(cards)
    }

    fun dealCard(): Card = if (cards.isNotEmpty()) cards.removeAt(0) else throw NoSuchElementException("Deck is empty")
}
