package blackjack.fixture

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit

fun createCard(
    type: Suit = Suit.SPADE,
    rank: Rank = Rank.SIX,
) = Card(type, rank)
