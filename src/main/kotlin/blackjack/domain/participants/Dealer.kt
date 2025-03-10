package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.GameState

class Dealer(
    private val deck: Deck,
) : Participant() {
    fun draw(): Card = deck.draw()

    override fun shouldHit(): Boolean = gameState != GameState.BUST && gameState != GameState.STAY
}
