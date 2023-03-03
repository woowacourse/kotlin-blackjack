package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsValue() >= BLACK_JACK_SCORE) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawState {
        cards.draw()

        return isPossibleToDrawAdditionalCard()
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
