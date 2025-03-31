package blackjack.domain

class Deck private constructor(
    private val _cards: ArrayDeque<Card>,
) {
    val cards: List<Card> get() = _cards

    companion object {
        fun createShuffled(): Deck = Deck(generateShuffledCards())

        fun createCustomDeck(customCards: ArrayDeque<Card>): Deck = Deck(customCards)

        private fun generateShuffledCards(): ArrayDeque<Card> =
            Rank.entries
                .flatMap { rank ->
                    Suit.entries.map { suit ->
                        Card(rank, suit)
                    }
                }.shuffled()
                .toCollection(ArrayDeque())
    }

    fun draw(): Card {
        if (_cards.isEmpty()) {
            refillDeck()
        }
        return _cards.removeLast()
    }

    private fun refillDeck() {
        _cards.addAll(generateShuffledCards())
    }
}
