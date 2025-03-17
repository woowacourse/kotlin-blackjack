package blackjack.model.user

import blackjack.model.GameJudge
import blackjack.model.state.GameStatus.BLACKJACK

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isAvailDrawCard(): Boolean = hand.score.isDrawableCardByDealer()

    fun isBlackjack(): Boolean = GameJudge.judge(hand) == BLACKJACK

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CARD_MINIMUM_SCORE = 17
    }
}
