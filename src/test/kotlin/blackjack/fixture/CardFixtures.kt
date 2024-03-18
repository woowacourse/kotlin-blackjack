package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit

fun createCard(
    type: Suit = Suit.SPADE,
    rank: Rank = Rank.SIX,
) = Card(type, rank)

val ACE_CARD = createCard(rank = Rank.ACE)
val FIVE_CARD = createCard(rank = Rank.FIVE)
val SEVEN_CARD = createCard(rank = Rank.SEVEN)
val TEN_CARD = createCard(rank = Rank.TEN)
val QUEEN_CARD = createCard(rank = Rank.QUEEN)
