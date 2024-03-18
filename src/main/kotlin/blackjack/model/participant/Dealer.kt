package blackjack.model.participant

import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Card
import blackjack.state.State

class Dealer(initState: State) : Participant(DEALER_NAME, INITIAL_DEALER_BETTING, initState) {
    override fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    ) = play(
        onHitCondition = { sumScore() < HIT_CONDITION },
        onDraw = onDraw,
        onDone = onDone,
    )

    override fun judge(
        betting: Betting,
        other: Participant,
    ): Profit {
        val otherBet = other.betting
        if (other.isBust()) return Profit(otherBet.amount)
        return super.judge(otherBet, other)
    }

    fun judge(others: List<Participant>): Profit {
        return others
            .map { judge(it.betting, it) }
            .reduce { acc, profit -> acc + profit }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_CONDITION = 17
        private val INITIAL_DEALER_BETTING = Betting(0)
    }
}
