package blackjack.domain.player

import blackjack.domain.BattingMoney
import blackjack.domain.card.Cards

class Player(
    //TODO: 딜러와 플레이어가 공통적으로 나타나는 부분을 찾고 코드 재사용 할 수 있게
    val name: PlayerName,
    val battingMoney: BattingMoney,
    val checkCards: (player: Player) -> Unit,
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
        checkCards(this)

        return isPossibleToDrawAdditionalCard() == DrawState.POSSIBLE
    }

    fun checkIsDrawnNothing() {
        if (cards.size == Cards.INITIAL_CARDS_SIZE) {
            checkCards(this)
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
