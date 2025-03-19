package blackjack.domain.model.hand.state

import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.strategy.StayStrategy

abstract class Playing(hands: Hands) : Initial(hands) {
    abstract val stayStrategy: StayStrategy

    override fun stay(): Finished = Stay(hands)

    override fun isFinished(): Boolean = false
}
