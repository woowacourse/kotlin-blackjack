package domain

class Referee(private val dealerScore: Int, private val userScore: List<Int>) {

    fun getResult(): List<GameResult> {
        return userScore.map { userScore ->
            calculateResult(userScore)
        }
    }

    private fun calculateResult(score: Int): GameResult {
        if (isDraw(score))
            return GameResult.DRAW
        if (isLose(score))
            return GameResult.LOSE
        return GameResult.WIN
    }

    private fun isDraw(score: Int) =
        ((dealerScore > SCORE_CONDITION) and (score > SCORE_CONDITION)) or (dealerScore == score)

    private fun isLose(score: Int) =
        ((dealerScore > score) and (dealerScore <= SCORE_CONDITION)) or (score > SCORE_CONDITION)

    companion object {
        private const val SCORE_CONDITION = 21
    }
}
