package blackjack.model.participant

import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Card
import blackjack.state.State

class Dealer(betting: Betting, initState: State) : Participant(DEALER_NAME, betting, initState) {
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
        if (isBust()) return -Profit(otherBet.amount)
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
    }
}
