package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit

fun createCard(
    type: Suit = Suit.SPADE,
    rank: Rank = Rank.SIX,
) = Card(type, rank)
