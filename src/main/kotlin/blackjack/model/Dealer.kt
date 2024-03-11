package blackjack.model

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > CardHandState.BLACKJACK.precondition -> CardHandState.BURST
            sum == CardHandState.BLACKJACK.precondition -> CardHandState.BLACKJACK
            sum <= DEALER_MAX_HIT_SUM -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }

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
            dealerSum == playerSum -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
    }

    companion object {
        private const val DEALER = "딜러"
        private const val DEALER_MAX_HIT_SUM = 16
    }
}
