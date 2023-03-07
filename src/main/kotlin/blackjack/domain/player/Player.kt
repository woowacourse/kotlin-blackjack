package blackjack.domain.player

import blackjack.domain.BattingMoney
import blackjack.domain.card.Cards

//TODO: 인자 갯수를 어떻게 줄여야 할까
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

    fun drawCard(
        checkCurrentCards: (player: Player) -> Unit = { }
    ): Boolean {

        cards.draw()
        checkCurrentCards(this)

        return isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE
    }

    fun checkIsDrawnNothing(
        checkCurrentCards: (player: Player) -> Unit = { },
    ) {
        if (cards.size == Cards.INITIAL_CARDS_SIZE) {
            checkCurrentCards(this)
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
