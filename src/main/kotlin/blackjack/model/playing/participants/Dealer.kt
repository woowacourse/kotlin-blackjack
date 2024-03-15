package blackjack.model.playing.participants

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.PlayerWinning
import blackjack.model.winning.WinningResultStatus

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand) {
    override fun canDraw(): Boolean = cardHand.getDealerState() == CardHandState.HIT

    fun judgePlayerWinningResult(playerResult: Map<PlayerName, Int>): PlayerWinning =
        PlayerWinning(
            playerResult.mapValues { (_, playerSum) ->
                determineGameResult(playerSum)
            },
        )

    private fun determineGameResult(playerSum: Int): WinningResultStatus {
        val dealerSum = cardHand.sum()

        return when {
            playerSum > BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            dealerSum > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.PUSH
            else -> WinningResultStatus.VICTORY
        }
    }

    companion object {
        private const val DEALER = "딜러"
        private const val BLACK_JACK_SCORE = 21
    }
}
