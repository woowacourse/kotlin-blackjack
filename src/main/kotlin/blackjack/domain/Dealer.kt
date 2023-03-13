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

    fun drawCard(cardPack: CardPack): DrawResult {
        if (cardPack.isEmpty()) {
            cardPack.addCardDeck()
        }

        if (isPossibleToDraw() == DrawState.POSSIBLE) {
            cardHand.draw(cardPack.draw())

            return DrawResult.Success
        }

        return DrawResult.Failure
    }

    companion object {
        private const val DEALER_UPPER_DRAW_CONDITION = 17
    }
}
