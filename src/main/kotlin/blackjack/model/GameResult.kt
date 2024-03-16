package blackjack.model

data class GameResult(
    private val dealerResult: Map<Result, Int>,
    private val playerResults: Map<Player, Result>,
) {
    fun getDealerResult(result: Result): Int {
        return dealerResult[result] ?: 0
    }

    fun getPlayerResult(player: Player): Result? {
        return playerResults[player]
    }

    fun settleBettingMoneys(): List<Revenue> {
        return playerResults.map { (player, result) ->
            val payout =
                settleBettingPayout(
                    result = result,
                    isBlackJackState = player.checkBlackJackState(),
                )
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
}
