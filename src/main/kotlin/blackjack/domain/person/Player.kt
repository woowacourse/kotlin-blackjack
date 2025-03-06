package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.card.Deck
import blackjack.domain.state.PlayerState

class Player(
    val name: String,
) : Person() {
    init {
        setGameState(PlayerState.FIRST_TURN)
    }

    fun draw(
        deck: Deck,
        isHit: Boolean,
    ) {
        val amount = if (gameState == PlayerState.FIRST_TURN) GameRule.FIRST_TURN_DRAW_AMOUNT else GameRule.HIT_DRAW_AMOUNT

        if (isHit) {
            repeat(amount) { hand.addCard(deck.draw()) }
            updateGameState()

            return
        }
        setGameState(PlayerState.STAY)
    }

    override fun updateGameState() {
        setGameState(PlayerState.from(this))
    }
}
