package blackjack.domain

class Dealer(val cards: Cards = Cards()) {

    private fun isPossibleToDraw(): DrawState {
        if (cards.getTotalCardsValue() >= DEALER_UPPER_DRAW_CONDITION) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawResult {
        if (isPossibleToDraw() == DrawState.POSSIBLE) {
            cards.draw()

            return DrawResult.Success
        }

        return DrawResult.Failure
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
