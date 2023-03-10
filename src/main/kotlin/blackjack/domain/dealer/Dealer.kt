package blackjack.domain.dealer

import blackjack.domain.card.Cards
import blackjack.domain.gameResult.ProfitMoney
import blackjack.domain.player.DrawState

class Dealer(
    val cards: Cards = Cards(),
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsScore() >= DEALER_UPPER_DRAW_CONDITION) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(checkDrawResult: (DrawResult) -> Unit = {}) {
        if (isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE) {
            cards.draw()

            return checkDrawResult(DrawResult.Success)
        }

        return checkDrawResult(DrawResult.Failure)
    }

    // todo test
    fun judgeDealerGameResults(
        playersTotalProfitMoney: ProfitMoney
    ): ProfitMoney {

        return !playersTotalProfitMoney
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
