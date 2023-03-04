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

    fun drawCard(): Boolean {
        cards.draw()

        return isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE
    }

    fun isDrawnNothing(): Boolean = cards.size == Cards.INITIAL_CARDS_SIZE

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
