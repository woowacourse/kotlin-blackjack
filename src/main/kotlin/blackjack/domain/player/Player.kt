package blackjack.domain.player

import blackjack.domain.BattingMoney
import blackjack.domain.BlackJackGameParticipant
import blackjack.domain.card.Cards

data class Player(
    val name: PlayerName,
    val battingMoney: BattingMoney,
) : BlackJackGameParticipant() {

//    constructor(
//        name: String,
//        battingMoney: Int,
//    ) : this(PlayerName(name), BattingMoney(battingMoney))

    fun drawCard(checkCurrentCards: (player: Player) -> Unit = { }): Boolean {
        cards.draw()
        checkCurrentCards(this)

        return isPossibleToDrawAdditionalCard(BLACK_JACK_SCORE) == DrawState.POSSIBLE
    }

    fun checkIsDrawnNothing(checkCurrentCards: (player: Player) -> Unit = { }) {
        if (cards.size == Cards.INITIAL_CARDS_SIZE) {
            checkCurrentCards(this)
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
