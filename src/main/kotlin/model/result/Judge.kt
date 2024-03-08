package model.result

import model.participants.Dealer
import model.participants.HumanName
import model.participants.Players
import model.result.Point.Companion.compareTo

object Judge {
    fun getPlayersResult(
        players: Players,
        dealer: Dealer,
    ): PlayersResult {
        val dealerPoint = dealer.getPointIncludingAce()
        val playersResult = mutableMapOf<HumanName, ResultType>()

        players.players.forEach { player ->
            val playerPoint = player.getPointIncludingAce()
            val result = calculateResult(playerPoint, dealerPoint)
            playersResult[player.humanName] = result
        }

        return PlayersResult(playersResult)
    }

    private fun calculateResult(
        playerPoint: Point,
        dealerPoint: Point,
    ): ResultType {
        return when {
            playerPoint > 21 -> ResultType.LOSE
            dealerPoint > 21 -> ResultType.WIN
            playerPoint > dealerPoint -> ResultType.WIN
            playerPoint == dealerPoint -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    fun getDealerResult(playersResult: PlayersResult): DealerResult {
        val dealerResult = mutableMapOf<ResultType, Int>()
        playersResult.result.forEach {
            val key = switchResultForDealer(it.value)
            dealerResult[key] = (dealerResult[key] ?: 0) + 1
        }
        return DealerResult(dealerResult)
    }

    private fun switchResultForDealer(resultType: ResultType): ResultType {
        return when (resultType) {
            ResultType.WIN -> ResultType.LOSE
            ResultType.DRAW -> ResultType.DRAW
            ResultType.LOSE -> ResultType.WIN
        }
    }
}
