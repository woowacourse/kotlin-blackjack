package blackjack.domain

class Dealer(
    private val cardPack: CardPack,
    val cardHand: CardHand = CardHand(listOf(cardPack.draw(), cardPack.draw()))
) {

    private fun isPossibleToDraw(): DrawState {
        if (cardHand.getTotalCardsScore() >= DEALER_UPPER_DRAW_CONDITION) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawResult {
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
