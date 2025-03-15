package blackjack.model.user

import blackjack.model.GameJudge
import blackjack.model.ScoreCalculator
import blackjack.model.state.GameStatus.BLACKJACK

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isAvailDrawCard() = ScoreCalculator.sum(cards) < DEALER_DRAW_CARD_MINIMUM_SCORE

    fun isBlackjack(): Boolean = GameJudge.judge(cards) == BLACKJACK

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CARD_MINIMUM_SCORE = 17
    }
}
