package model

import model.human.Dealer
import model.human.HumanName
import model.human.Players

object Judge {
    fun getPlayersResult(
        players: Players,
        dealer: Dealer,
    ): Map<HumanName, ResultType> {
        val dealerPoint = dealer.getPointIncludingAce().amount
        val playersResultType = mutableMapOf<HumanName, ResultType>()

        players.players.forEach { player ->
            val playerPoint = player.getPointIncludingAce().amount
            val result = calculateResult(playerPoint, dealerPoint)
            playersResultType[player.humanName] = result
        }

        return playersResultType
    }

    private fun calculateResult(
        playerPoint: Int,
        dealerPoint: Int,
    ): ResultType {
        return when {
            playerPoint > 21 -> ResultType.LOSE
            dealerPoint > 21 -> ResultType.WIN
            playerPoint > dealerPoint -> ResultType.WIN
            playerPoint == dealerPoint -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    fun getDealerResult(playersResultType: Map<HumanName, ResultType>): Map<ResultType, Int> {
        val dealerResultType = mutableMapOf<ResultType, Int>()
        playersResultType.forEach {
            val key = switchResult(it.value)
            dealerResultType[key] = (dealerResultType[key] ?: 0) + 1
        }
        return dealerResultType
    }

    private fun switchResult(resultType: ResultType): ResultType {
        return when (resultType) {
            ResultType.WIN -> ResultType.LOSE
            ResultType.DRAW -> ResultType.DRAW
            ResultType.LOSE -> ResultType.WIN
        }
    }
}
