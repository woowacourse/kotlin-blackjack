package blackjack.domain.participant

import blackjack.domain.Betting
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Busted
import blackjack.domain.state.Finished
import blackjack.domain.state.ParticipantState
import blackjack.domain.state.Stay

class Player(
    val name: String,
    private val betting: Betting,
    deck: Deck,
) : Participant(deck) {
    init {
        ready()
    }

    fun calculateProfit(dealerState: ParticipantState): Double {
        val gameResult = getResult(dealerState)
        return gameResult.calculateProfit(betting.amount)
    }

    private fun ready() {
        hit()
    }

    private fun getResult(dealerState: ParticipantState): GameResult {
        val playerState = state
        check(playerState is Finished) { "Player's turn is not finished" }
        check(dealerState is Finished) { "Dealer's turn is not finished" }

        return when (playerState) {
            is Blackjack -> getResultWhenBlackjack(dealerState)
            is Busted -> getResultWhenBusted()
            is Stay -> getResultWhenBusted(dealerState)
        }
    }

    private fun getResultWhenBlackjack(dealerState: Finished): GameResult =
        when (dealerState) {
            is Blackjack -> GameResult.DRAW
            is Busted -> GameResult.BLACKJACK_WIN
            is Stay -> GameResult.BLACKJACK_WIN
        }

    private fun getResultWhenBusted(): GameResult = GameResult.LOSE

    private fun getResultWhenBusted(dealerState: Finished): GameResult =
        when (dealerState) {
            is Blackjack -> GameResult.LOSE
            is Busted -> GameResult.WIN
            is Stay -> getResultWhenBothStay(dealerState)
        }

    private fun getResultWhenBothStay(dealerState: Stay): GameResult =
        when {
            score > dealerState.score -> GameResult.WIN
            score < dealerState.score -> GameResult.LOSE
            else -> GameResult.DRAW
        }
}
