package blackjack.model

class Dealer(wallet: Wallet = Wallet(DEFAULT_DEALER_NAME)) : Participant(wallet) {
    fun openFirstCard(): Card? {
        return getCards().firstOrNull()
    }

    fun settleBettingMoneys(
        players: List<Player>,
        results: List<Result>,
    ) {
        players.zip(results).forEach { (player, result) ->
            val payout = settleBettingPayout(
                result = result,
                isBlackJackState = player.checkBlackJackState()
            )
            val playerBettingResultMoney = player.getBettingMoney() * payout.toInt()
            val dealerBettingMoney = getBettingMoney()
            settleBettingMoney(dealerBettingMoney - playerBettingResultMoney)
            player.settleBettingMoney(playerBettingResultMoney)
        }
    }

    private fun settleBettingPayout(
        result: Result,
        isBlackJackState: Boolean,
    ): Float {
        return when (result) {
            Result.WIN -> {
                if (isBlackJackState) {
                    1.5f
                } else {
                    1f
                }
            }

            Result.DRAW -> {
                if (isBlackJackState) {
                    1.5f
                } else {
                    1f
                }
            }

            Result.LOSE -> -1f
        }
    }

    override fun openInitCards(): List<Card> {
        return getCards().firstOrNull()?.let { listOf(it) } ?: listOf()
    }

    override fun checkShouldDrawCard(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
