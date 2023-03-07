package blackjack.domain.dealer

import blackjack.domain.BlackJackGameParticipant
import blackjack.domain.player.DrawState

class Dealer: BlackJackGameParticipant() {

    fun drawCard(): DrawResult {
        if (isPossibleToDrawAdditionalCard(DEALER_UPPER_DRAW_CONDITION) == DrawState.POSSIBLE) {
            cards.draw()

            return DrawResult.Success
        }

        return DrawResult.Failure
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
