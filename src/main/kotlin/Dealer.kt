class Dealer(val cards: Cards = Cards()) {

    private fun isPossibleToDraw(): Boolean {
        if (cards.getTotalCardsValue() >= 17) {
            return false
        }

        return true
    }

    fun drawCard(): DrawResult {
        if (isPossibleToDraw()) {
            cards.draw()

            return DrawResult.Success
        }

        return DrawResult.Failure
    }
}
