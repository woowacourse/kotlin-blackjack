package blackjack.domain.person

import blackjack.domain.state.PersonState

class Player(
    val name: String,
    val betAmount: Int = 0,
) : Person() {
    fun changeToStay() {
        state = PersonState.STAY
    }
}
