package model

object Judge {
    fun getPlayersResult(
        players: Players,
        dealer: Dealer,
    ): Map<Name, Result> {
        val dealerPoint = dealer.getPointIncludingAce().amount
        val playersResult = mutableMapOf<Name, Result>()

        players.players.forEach { player ->
            val playerPoint = player.getPointIncludingAce().amount
            val result = calculateResult(playerPoint, dealerPoint)
            playersResult[player.name] = result
        }

        return playersResult
    }

    private fun calculateResult(
        playerPoint: Int,
        dealerPoint: Int,
    ): Result {
        return when {
            playerPoint > 21 -> Result.LOSE
            dealerPoint > 21 -> Result.WIN
            playerPoint > dealerPoint -> Result.WIN
            playerPoint == dealerPoint -> Result.DRAW
            else -> Result.LOSE
        }
    }

    fun getDealerResult(playersResult: Map<Name, Result>): Map<Result, Int> {
        val dealerResult = mutableMapOf<Result, Int>()
        playersResult.forEach {
            val key = switchResult(it.value)
            dealerResult[key] = (dealerResult[key] ?: 0) + 1
        }
        return dealerResult
    }

    private fun switchResult(result: Result): Result {
        return when (result) {
            Result.WIN -> Result.LOSE
            Result.DRAW -> Result.DRAW
            Result.LOSE -> Result.WIN
        }
    }
}
