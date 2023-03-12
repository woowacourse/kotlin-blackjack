package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(val name: String, override var state: State, val bettingAmount: Int) : Participant {

    override fun isOverCondition(): Boolean = state !is Hit

    fun getConsequence(dealer: Dealer): Consequence {
        return when (compareValuesBy(this, dealer, { it.state }, { it.state.hand.getTotalScore() })) {
            1 -> Consequence.WIN
            0 -> Consequence.DRAW
            -1 -> Consequence.LOSE
            else -> throw IllegalStateException(INVALID_COMPARE_ERROR)
        }
    }

    companion object {
        private const val INVALID_COMPARE_ERROR = "결과비교의 문제가 있습니다"
    }
}
