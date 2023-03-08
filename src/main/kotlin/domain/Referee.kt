package domain

import view.GameResult

class Referee(private val dealerScore: Int, private val userScore: List<Int>) {

    fun getResult(): List<GameResult> {
        return userScore.map { userScore ->
            calculateResult(userScore)
        }
    }

    private fun calculateResult(userScore: Int): GameResult {
        val dealerOverMaxScore = dealerScore > GAME_MAX_SCORE
        val playerOverMaxScore = userScore > GAME_MAX_SCORE

        return when {
            isDealerBlackJack() -> GameResult.LOSE
            (dealerOverMaxScore and playerOverMaxScore) or (dealerScore == userScore) -> GameResult.DRAW
            ((dealerScore > userScore) and !dealerOverMaxScore or playerOverMaxScore) -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }

    private fun isDealerBlackJack(): Boolean = dealerScore == GAME_MAX_SCORE

    companion object {
        private const val GAME_MAX_SCORE = 21
    }
}
