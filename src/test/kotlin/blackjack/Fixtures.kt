package blackjack

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit

object Fixtures {
    val HEART_ACE = Card(Suit.HEART, Rank.ACE)
    val HEART_TWO = Card(Suit.HEART, Rank.TWO)
    val HEART_THREE = Card(Suit.HEART, Rank.THREE)
    val HEART_SIX = Card(Suit.HEART, Rank.SIX)
    val HEART_JACK = Card(Suit.HEART, Rank.JACK)
    val HEART_QUEEN = Card(Suit.HEART, Rank.QUEEN)
    val HEART_KING = Card(Suit.HEART, Rank.KING)

    val SPADE_ACE = Card(Suit.SPADE, Rank.ACE)
    val SPADE_TWO = Card(Suit.SPADE, Rank.TWO)
    val SPADE_THREE = Card(Suit.SPADE, Rank.THREE)
    val SPADE_JACK = Card(Suit.SPADE, Rank.JACK)
}
