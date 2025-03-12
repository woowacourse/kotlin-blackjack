package blackjack.domain.person

import blackjack.domain.BetAmount
import blackjack.domain.state.PersonState

class Player(
    val name: String,
    val betAmount: BetAmount = BetAmount(),
) : Person() {
    fun changeToStay() {
        state = PersonState.STAY
    }
}
