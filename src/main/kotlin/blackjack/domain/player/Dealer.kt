package blackjack.domain.player

import blackjack.domain.Result

class Dealer(name: String = "딜러") : Player(name) {

    val results: MutableMap<Result, Int> = Result.values().associateWith { 0 }.toMutableMap()

    fun updateResults(othersSum: List<Int>) {
        othersSum.forEach {
            val result = calculateResult(it)
            results[result] = results[result]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    override fun checkProvideCardPossible(): Boolean {
        if (cards.sumCardsNumber() <= PARTICIPANT_MORE_CARD_CRITERIA) return true
        return false
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 16
    }
}
