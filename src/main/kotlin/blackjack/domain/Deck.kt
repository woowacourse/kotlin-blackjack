package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank

class Deck(
    shuffler: Shuffler,
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

    private var cards: List<Card> = shuffler.shuffle(aceCards + numberCards + faceCards)

    fun draw(): Card {
        val card = cards.first()
        cards = cards.minus(card)
        return card
    }
}
