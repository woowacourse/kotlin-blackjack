package blackjack.domain.model.participant.fixture

import blackjack.domain.model.participant.Dealer

object Dealers {
    val BLACKJACK = Dealer(listOf(Cards.ACE, Cards.JACK))
    val SCORE_19 = Dealer(listOf(Cards.NINE, Cards.KING))
    val SCORE_18 = Dealer(listOf(Cards.EIGHT, Cards.QUEEN))
}
