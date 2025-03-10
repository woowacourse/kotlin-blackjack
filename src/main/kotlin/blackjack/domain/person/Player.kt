package blackjack.domain.person

import blackjack.domain.card.Deck
import blackjack.domain.state.PlayerState

class Player(
    val name: String,
    hand: Hand,
) : Person(hand) {
    init {
        state = PlayerState.HIT
    }

    constructor(name: String) : this(name = name, hand = Hand())

    override fun draw(deck: Deck) {
        super.draw(deck)
        state = PlayerState.from(this)
    }

    fun changeToStay() {
        state = PlayerState.STAY
    }
}
