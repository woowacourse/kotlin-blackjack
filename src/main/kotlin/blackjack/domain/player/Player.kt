package blackjack.domain.player

import blackjack.domain.card.Cards

data class Player(
    val name: PlayerName,
    val battingMoney: BattingMoney,
    val cards: Cards = Cards(),
) {

    constructor(
        name: String,
        battingMoney: Int,
    ) : this(PlayerName(name), BattingMoney(battingMoney))

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsScore() >= BLACK_JACK_SCORE) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(checkCurrentCards: (player: Player) -> Unit = { }): Boolean {
        cards.draw()
        checkCurrentCards(this)

        return isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE
    }

    fun checkIsDrawnNothing(checkCurrentCards: (player: Player) -> Unit = { }) {
        if (cards.isDrawnNothing()) {
            checkCurrentCards(this)
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
