package blackjack.domain

class Dealer(
    val cardHand: CardHand
) {

    private fun isPossibleToDraw(): DrawState {
        if (cardHand.getTotalCardsScore() >= DEALER_UPPER_DRAW_CONDITION) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(card: Card): DrawResult {
        if (isPossibleToDraw() == DrawState.POSSIBLE) {
            cardHand.draw(card)

            return DrawResult.Success
        }

        return DrawResult.Failure
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
