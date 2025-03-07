package blackjack.domain.person

import blackjack.domain.card.Deck
import blackjack.domain.state.PlayerState

class Player(
    val name: String,
    hand: Hand,
) : Person(hand) {
    init {
        gameState = PlayerState.FIRST_TURN
    }

    constructor(name: String) : this(name = name, hand = Hand())

    override fun draw(deck: Deck) {
        val amount = getDrawAmount(PlayerState.FIRST_TURN)
        repeat(amount) {
            hand.addCard(deck.draw())
        }
        gameState = PlayerState.from(this)
    }

    fun changeToStay() {
        gameState = PlayerState.STAY
    }
}
