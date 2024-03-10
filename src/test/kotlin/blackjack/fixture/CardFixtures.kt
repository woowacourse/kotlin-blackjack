package blackjack.fixture

import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.card.Card

fun createCard(
    type: Suit = Suit.SPADE,
    rank: Rank = Rank.SIX,
) = Card(type, rank)
