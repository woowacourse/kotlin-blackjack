package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(val name: String, override var state: State, val bettingAmount: Int) : Participant {

    override fun isOverCondition(): Boolean = state !is Hit
}
