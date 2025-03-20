package blackjack.domain.model.participant.fixture

import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.bet.BetAmount

object Players {
    val BLACKJACK_1000BET = Player(betAmount = BetAmount(1000), listOf(Cards.ACE, Cards.JACK))
    val NORMAL_21_1000BET = Player(betAmount = BetAmount(1000), listOf(Cards.NINE, Cards.KING, Cards.TWO))
    val SCORE_19_1000BET = Player(betAmount = BetAmount(1000), listOf(Cards.NINE, Cards.KING))
    val SCORE_18_1000BET = Player(betAmount = BetAmount(1000), listOf(Cards.EIGHT, Cards.KING))
    val BUST_1000BET = Player(betAmount = BetAmount(1000), listOf(Cards.KING, Cards.SIX, Cards.QUEEN))
}
