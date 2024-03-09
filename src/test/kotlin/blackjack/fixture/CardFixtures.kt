package blackjack.fixture

import blackjack.model.Rank
import blackjack.model.Suit
import blackjack.model.card.Card

fun createCard(
    type: Suit = Suit.SPADE,
    rank: Rank = Rank.SIX,
) = Card(type, rank)
