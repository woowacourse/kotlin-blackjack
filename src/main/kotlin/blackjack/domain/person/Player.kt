package blackjack.domain.person

import blackjack.domain.state.PersonState

class Player(val name: String) : Person() {
    fun changeToStay() {
        state = PersonState.STAY
    }
}
