package blackjack.domain

class Player(
    val name: PlayerName,
    val cards: Cards = Cards()
) {

    constructor(name: String) : this(PlayerName(name))

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsScore() >= BLACK_JACK_SCORE) {
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
