package blackjack.model.participant

import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Card
import blackjack.state.State

class Player(
    name: String,
    betting: Betting,
    initState: State,
    private val onHitCondition: (String) -> Boolean,
) : Participant(name, betting, initState) {
    override fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    ) = play(
        onHitCondition = { onHitCondition(name) },
        onDraw = onDraw,
        onDone = onDone,
    )

    override fun judge(
        betting: Betting,
        other: Participant,
    ): Profit {
        if (isBust()) return -Profit(this.betting.amount)
        return super.judge(this.betting, other)
    }
}
