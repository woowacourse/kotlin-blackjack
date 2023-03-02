class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsValue() > 21) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawState {
        cards.draw()

        return isPossibleToDrawAdditionalCard()
    }
}
