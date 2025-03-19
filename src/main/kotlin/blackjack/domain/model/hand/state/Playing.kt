package blackjack.domain.model.hand.state

import blackjack.domain.model.hand.Hands

abstract class Playing(hands: Hands) : BaseState(hands) {
    override fun stay(): Finished = Stay(hands)

    override fun isFinished(): Boolean = false
}
