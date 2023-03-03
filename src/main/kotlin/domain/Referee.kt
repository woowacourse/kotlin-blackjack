package domain

class Referee(private val dealerScore: Int, private val userScore: List<Int>) {

    fun getResult(): List<GameResult> {
        return userScore.map { userScore ->
            calculateResult(userScore)
        }
    }

    private fun calculateResult(score: Int): GameResult {
        if (((dealerScore > SCORE_CONDITION) and (score > SCORE_CONDITION)) or (dealerScore == score))
            return GameResult.DRAW
        if (((dealerScore > score) and (dealerScore <= SCORE_CONDITION)) or (score > SCORE_CONDITION))
            return GameResult.LOSE
        return GameResult.WIN
    }

    companion object {
        private const val SCORE_CONDITION = 21
    }
}
