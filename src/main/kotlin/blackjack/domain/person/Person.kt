package blackjack.domain.person

import blackjack.domain.GameState
import blackjack.domain.card.Deck
import blackjack.domain.score.ScoreCalculator

abstract class Person(val name: String) {
    val hand: Hand = Hand()

    var gameState: GameState = GameState.HIT

    fun draw(
        deck: Deck,
        amount: Int,
    ) {
        repeat(amount) { hand.addCard(deck.draw()) }
        updateGameState()
    }

    private fun updateGameState() {
        val score = ScoreCalculator.calculate(hand)
        gameState = GameState.from(score)
        println("\n $name / $score / $gameState \n")
    }
}
