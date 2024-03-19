package blackjack.model.playing.participants.player

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.Role
import blackjack.model.winning.Betting
import blackjack.model.winning.WinningResultStatus

data class Player(
    override val name: PlayerName,
    override val cardHand: CardHand,
    var betting: Betting =
        Betting(
            0,
        ),
) : Role(name, cardHand) {
    override fun canDraw(): Boolean = cardHand.readIsHit()

    fun calculateProfit(winningResultStatus: WinningResultStatus): Double {
        return when (winningResultStatus) {
            WinningResultStatus.PUSH -> PUSH_MULTIPLIER
            WinningResultStatus.DEFEAT -> betting.amount * DEFEAT_MULTIPLIER
            WinningResultStatus.VICTORY ->
                if (cardHand.isBlackjack(this)) {
                    betting.amount * BLACK_JACK_MULTIPLIER
                } else {
                    betting.amount * VICTORY_MULTIPLIER
                }
        }
    }

    companion object {
        private const val BLACK_JACK_MULTIPLIER = 1.5
        private const val VICTORY_MULTIPLIER = 1.0
        private const val DEFEAT_MULTIPLIER = -1.0
        private const val PUSH_MULTIPLIER = 0.0
    }
}
