package blackjack.domain.person

import blackjack.const.GameRule
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
        isHit: Boolean = true,
    ) {
        val amount = if (gameState == PlayerState.FIRST_TURN) GameRule.FIRST_TURN_DRAW_AMOUNT else GameRule.HIT_DRAW_AMOUNT

        if (isHit) {
            repeat(amount) {
                hand.addCard(deck.draw())
            }
            gameState = PlayerState.from(this)

            return
        }
        gameState = PlayerState.STAY
    }
}
