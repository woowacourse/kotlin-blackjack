package blackjack.domain

class Deck(cards: List<Card>) {
    val cards: ArrayDeque<Card> = ArrayDeque(cards)

    fun pick(): Card = cards.removeLast()

    companion object {
        fun create(): Deck = Deck(CARDS.shuffled())

        private val CARDS: List<Card> =
            Suit.entries
                .flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }
    }
}