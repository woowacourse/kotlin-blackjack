package blackjack.model.card

class Deck(cards: List<Card> = generateStandardDeck()) {
    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    init {
        _cards.shuffle()
    }

    companion object {
        private fun generateStandardDeck(): MutableList<Card> {
            return Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination ->
                    Card(denomination, suit)
                }
            }.toMutableList()
        }
    }

    fun doubleDealCard() = listOf(dealCard(), dealCard())

    fun dealCard(): Card {
        if (_cards.isEmpty()) {
            _cards.addAll(generateStandardDeck())
            _cards.shuffle()
        }
        return _cards.removeAt(0)
    }
}
