package blackjack.domain

class Deck(
    shuffler: Shuffler,
) {
    private val aceCards: List<Card> = Suit.entries.map { suit -> Card(Ace, suit) }
    private val numberCards: List<Card> =
        Suit.entries.flatMap { suit ->
            Number.entries.map { number: Number ->
                Card(number, suit)
            }
        }
    private val faceCards: List<Card> =
        Suit.entries.flatMap { suit ->
            Face.entries.map { face -> Card(face, suit) }
        }

    private var cards: List<Card> = shuffler.shuffle(aceCards + numberCards + faceCards)

    fun draw(): Card {
        val card = cards.first()
        cards = cards.minus(card)
        return card
    }
}
