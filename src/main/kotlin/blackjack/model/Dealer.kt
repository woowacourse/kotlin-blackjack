package blackjack.model

class Dealer(userInformation: UserInformation = UserInformation(DEFAULT_DEALER_NAME)) : Participant(userInformation) {
    fun openFirstCard(): Card? {
        return getCards().firstOrNull()
    }

    fun settleBettingMoneys(gameResult: GameResult): List<Revenue> {
        return gameResult.getResultPlayers().map { player ->
            val payout =
                gameResult.getPlayerResult(player)?.let { result ->
                    settleBettingPayout(
                        result = result,
                        isBlackJackState = player.checkBlackJackState(),
                    )
                } ?: 0
            val playerBettingResultMoney = player.getBettingMoney() * payout.toInt()
            Revenue(
                player.getName(),
                playerBettingResultMoney,
            )
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
