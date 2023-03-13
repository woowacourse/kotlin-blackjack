package blackjack.domain.betting

import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult
import blackjack.domain.result.ResultType

class BettingResult
private constructor(private val bettingResult: Map<Player, Int>) {
    fun getPlayerEarningMoney(player: Player) = bettingResult[player]
    fun getDealerEarningMoney(): Int = -bettingResult.values.sum()

    companion object {
        private const val BLACKJACK_EARNING_RATE = 1.5
        private const val TIE_EARNING_RATE = 1.0
        private const val LOSE_EARNING_RATE = -1.0

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
                ResultType.WIN -> money * BLACKJACK_EARNING_RATE
                ResultType.TIE -> money * TIE_EARNING_RATE
                ResultType.LOSE -> money * LOSE_EARNING_RATE
                null -> 0
            }
        }
    }
}
