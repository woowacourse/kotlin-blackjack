package model.domain.result

import model.domain.player.User
import model.domain.result.GameResult.* // ktlint-disable no-wildcard-imports
import model.domain.state.gameover.BlackJack
import model.tools.Participant

class Profit(participants: Participant) {
    private val dealer = participants.dealer
    private val users = participants.user

    fun calculate() {
        userProfit()
        dealerProfit()
    }

    private fun userProfit() {
        users.forEach { user ->
            getUserGameResult(user)
        }
    }

    private fun getUserGameResult(user: User) {
        when (calculateResult(user.getTotalScore())) {
            WIN -> isUserBlackJackWhenWin(user)
            LOSE -> user.lose()
            DRAW -> user.draw()
        }
    }

    private fun isUserBlackJackWhenWin(user: User) =
        if (user.state is BlackJack) user.winnerWinnerChickenDinner() else user.win()

    private fun dealerProfit() {
        val userProfit = users.sumOf { it.money.amount }
        dealer.calculateTotalMoney(userProfit)
    }

    private fun calculateResult(userScore: Int): GameResult {
        val dealerScore = dealer.getTotalScore()
        val dealerOverMaxScore = dealerScore > GAME_MAX_SCORE
        val playerOverMaxScore = userScore > GAME_MAX_SCORE

        return when {
            isALLBlackJack(dealerScore, userScore) -> DRAW
            dealerOverMaxScore -> WIN
            !dealerOverMaxScore && !playerOverMaxScore && dealerScore == userScore -> DRAW
            !playerOverMaxScore and (dealerScore > userScore) -> LOSE
            !dealerOverMaxScore && !playerOverMaxScore && dealerScore < userScore -> WIN
            else -> LOSE
        }
    }

    private fun isALLBlackJack(dealerScore: Int, userScore: Int): Boolean =
        (dealerScore == GAME_MAX_SCORE) and (userScore == GAME_MAX_SCORE)

    companion object {
        private const val GAME_MAX_SCORE = 21
    }
}
