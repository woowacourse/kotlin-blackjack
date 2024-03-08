package blackjack.model.card

object Deck {
    val cards: MutableList<Card> = mutableListOf()

    init {
        initialize()
    }

    fun initialize() {
        cards.clear()
        cards.addAll(
            Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination ->
                    Card(denomination, suit)
                }
            },
        )
        cards.shuffle()
    }

    fun dealCard(): Card = cards.removeAt(0)
}
