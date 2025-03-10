package blackjack.domain.participants

import blackjack.domain.state.GameState

class Player(
    val name: String,
    private val checkHit: (String) -> Boolean,
) : Participant() {
    override fun shouldHit(): Boolean {
        return checkHit(name) && gameState != GameState.BUST
    }
}
