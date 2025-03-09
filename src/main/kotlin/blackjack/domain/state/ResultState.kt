package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player

enum class ResultState {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun calculateWin(
            player: Player,
            dealer: Dealer,
        ): ResultState {
            val playerScore = player.calculateScore()
            val dealerScore = dealer.calculateScore()

            if (playerScore > GameRule.BLACKJACK_SCORE) return LOSE
            if (dealerScore > GameRule.BLACKJACK_SCORE) return WIN

            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }

        private fun Person.calculateScore(): Int = ScoreCalculator.calculate(this.cards())
    }
}
