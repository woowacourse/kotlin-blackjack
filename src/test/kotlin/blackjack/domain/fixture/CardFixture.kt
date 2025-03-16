package blackjack.domain.fixture

import blackjack.domain.card.Card
import blackjack.domain.card.Rank.AceRank
import blackjack.domain.card.Rank.FaceRank
import blackjack.domain.card.Suit

val CARD_ACE_SPADE = Card(AceRank, Suit.SPADE)
val CARD_KING_SPADE = Card(FaceRank.KING, Suit.SPADE)
