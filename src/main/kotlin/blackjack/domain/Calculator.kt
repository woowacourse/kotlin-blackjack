package blackjack.domain

class Calculator {
    private fun getDividend(player: Player, consequence: Consequence): Int {
        return when (consequence) {
            Consequence.WIN -> player.bettingMoney.multipleTwo()
            Consequence.LOSE -> player.bettingMoney.multipleZero()
            Consequence.DRAW -> player.bettingMoney.multipleOne()
        }
    }

    fun calculateDividend(playerResultsBy: Map<Player, Consequence>): Map<Player, Int> {
        val result = mutableMapOf<Player, Int>()
        playerResultsBy.forEach {
            val dividend = getDividend(it.key, it.value)
            result[it.key] = it.key.getProfit(dividend)
        }
        return result
    }
}
