class Dealer(val cards: Cards = Cards()) {

    private fun isPossibleToDraw(): DrawState {
        if (cards.getTotalCardsValue() >= 17) {
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
}
