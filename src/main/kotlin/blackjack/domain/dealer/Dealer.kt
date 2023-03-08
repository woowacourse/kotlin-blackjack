package blackjack.domain.dealer

import blackjack.domain.card.Cards
import blackjack.domain.player.DrawState
import blackjack.domain.player.Player

class Dealer(
    val cards: Cards = Cards(),
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsScore() >= DEALER_UPPER_DRAW_CONDITION) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawResult {
        if (isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE) {
            cards.draw()

            return DrawResult.Success
        }

        return DrawResult.Failure
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
