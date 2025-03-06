package blackjack.domain.person

import blackjack.domain.card.Deck
import blackjack.domain.state.PlayerState

class Player(
    val name: String,
    hand: Hand,
) : Person(hand.copy()) {
    init {
        gameState = PlayerState.FIRST_TURN
    }

    constructor(name: String) : this(name = name, hand = Hand())

    fun draw(
        deck: Deck,
        hitFlag: Boolean = true,
    ) {
        val amount = getDrawAmount(PlayerState.FIRST_TURN)
        if (hitFlag) {
            repeat(amount) {
                hand.addCard(deck.draw())
            }
            gameState = PlayerState.from(this)
            return
        }
        gameState = PlayerState.STAY
    }
}
