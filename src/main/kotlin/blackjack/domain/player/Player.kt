package blackjack.domain.player

import blackjack.domain.BattingMoney
import blackjack.domain.card.Cards

class Player(
    val name: PlayerName,
    val battingMoney: BattingMoney,
    val checkCurrentCards: (player: Player) -> Unit = { },
    val cards: Cards = Cards(),
) {

    constructor(
        name: String,
        battingMoney: Int,
        checkCards: (player: Player) -> Unit,
    ) : this(PlayerName(name), BattingMoney(battingMoney), checkCards)

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cards.getMinimumCardsScore() >= BLACK_JACK_SCORE) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): Boolean {
        cards.draw()
        checkCurrentCards(this)

        return isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE
    }

    fun checkIsDrawnNothing() {
        if (cards.size == Cards.INITIAL_CARDS_SIZE) {
            checkCurrentCards(this)
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
