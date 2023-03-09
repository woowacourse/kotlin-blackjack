package blackjack.domain.betting

import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult
import blackjack.domain.result.ResultType

class BettingResult(private val bettingResult: Map<Player, Int>) {
    fun getPlayerEarningMoney(player: Player) = bettingResult[player]
    fun getDealerEarningMoney(): Int = -bettingResult.values.sum()

    companion object {
        fun of(players: List<Player>, gameResult: BlackjackResult): BettingResult {
            val results = mutableMapOf<Player, Int>()

            players.forEach {
                val resultType = gameResult.getResultOf(it)
                results[it] = getEarningMoney(it.bettingMoney, resultType)
            }

            return BettingResult(results)
        }

        private fun getEarningMoney(money: BettingMoney, resultType: ResultType?): Int {
            return when (resultType) {
                ResultType.WIN -> money * 1.5
                ResultType.TIE -> money * 1.0
                ResultType.LOSE -> money * -1.0
                null -> 0
            }
        }
    }
}
