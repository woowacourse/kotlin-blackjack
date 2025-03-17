package blackjack.domain.card

import blackjack.domain.card.Rank.AceRank
import blackjack.domain.card.Rank.FaceRank
import blackjack.domain.card.Rank.NumberRank

object TrumpCard {
    fun getNewCardPack(): List<Card> = aceCards + numberCards + faceCards

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
}
