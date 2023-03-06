package blackjack.domain.player

import blackjack.domain.Result

class Dealer(name: String = "딜러") : Player(name) {

    val results: MutableMap<Result, Int> = Result.values().associateWith { 0 }.toMutableMap()
    override val cardProvideCriteria = MIN_SUM_NUMBER

    fun updateResults(othersSum: List<Int>) {
        othersSum.forEach {
            val result = calculateResult(it)
            results[result] = results[result]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
