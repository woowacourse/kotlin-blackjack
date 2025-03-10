package blackjack.domain.person

import blackjack.domain.state.PersonState

class Player(
    val name: String,
    hand: Hand,
) : Person(hand) {
    constructor(name: String) : this(name = name, hand = Hand())

    fun changeToStay() {
        state = PersonState.STAY
    }
}
