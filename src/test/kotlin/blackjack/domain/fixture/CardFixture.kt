package blackjack.domain.fixture

import blackjack.domain.card.Card
import blackjack.domain.card.Rank.AceRank
import blackjack.domain.card.Rank.FaceRank
import blackjack.domain.card.Rank.NumberRank
import blackjack.domain.card.Suit

val CARD_ACE_SPADE = Card(AceRank, Suit.SPADE)
val CARD_KING_SPADE = Card(FaceRank.KING, Suit.SPADE)
val CARD_SEVEN_HEART = Card(NumberRank.SEVEN, Suit.HEART)
val CARD_SEVEN_DIAMOND = Card(NumberRank.SEVEN, Suit.DIAMOND)
