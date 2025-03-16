package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Rank.AceRank
import blackjack.domain.card.Rank.FaceRank
import blackjack.domain.card.Rank.NumberRank
import blackjack.domain.card.Suit

class Deck(
    private val shuffler: Shuffler,
) {
    private val aceCards: List<Card> = Suit.entries.map { suit -> Card(AceRank, suit) }
    private val numberCards: List<Card> =
        Suit.entries.flatMap { suit ->
            NumberRank.entries.map { number: NumberRank ->
                Card(number, suit)
            }
        }
    private val faceCards: List<Card> =
        Suit.entries.flatMap { suit ->
            FaceRank.entries.map { face: FaceRank -> Card(face, suit) }
        }

    private val cardPack = aceCards + numberCards + faceCards

    private var cards: List<Card> = emptyList()

    init {
        refillDeck()
    }

    fun take(): Card {
        val card: Card =
            cards.firstOrNull() ?: run {
                refillDeck()
                cards.first()
            }
        cards = cards.minus(card)
        return card
    }

    private fun refillDeck() {
        cards = shuffler.shuffle(cardPack)
    }
}
