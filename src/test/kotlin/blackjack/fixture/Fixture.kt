package blackjack.fixture

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit

object Fixture {
    val BLACK_JACK =
        arrayOf(
            Card.of(Rank.ACE, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
    val TWENTY_ONE =
        arrayOf(
            Card.of(Rank.TWO, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
        )
    val BUST =
        arrayOf(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.SIX, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
        )
}
