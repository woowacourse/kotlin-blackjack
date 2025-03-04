package blackjack.domain.person

import blackjack.domain.GameState
import blackjack.domain.card.Deck

abstract class Person(val name: String) {
    val hand: Hand = Hand()

    var gameState: GameState = GameState.HIT

    abstract fun draw(
        deck: Deck,
        amount: Int,
    )
}
