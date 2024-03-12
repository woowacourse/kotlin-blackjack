package blackjack.model.playing.participants

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.PlayerWinning
import blackjack.model.winning.WinningResultStatus

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > CardHandState.BLACKJACK.precondition -> CardHandState.BUST
            sum == CardHandState.BLACKJACK.precondition -> CardHandState.BLACKJACK
            sum <= DEALER_MAX_HIT_SUM -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }

    fun canDraw(): Boolean = this.getState() == CardHandState.HIT

    fun judgePlayerWinningResult(playerResult: Map<PlayerName, Int>): PlayerWinning =
        PlayerWinning(
            playerResult.mapValues { (_, playerSum) ->
                determineGameResult(playerSum)
            },
        )

    private fun determineGameResult(playerSum: Int): WinningResultStatus {
        val dealerSum = cardHand.sum()

        return when {
            playerSum > CardHandState.BLACKJACK.precondition -> WinningResultStatus.DEFEAT
            dealerSum > CardHandState.BLACKJACK.precondition -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.PUSH
            else -> WinningResultStatus.VICTORY
        }
    }

    companion object {
        private const val DEALER = "딜러"
        private const val DEALER_MAX_HIT_SUM = 16
    }
}
