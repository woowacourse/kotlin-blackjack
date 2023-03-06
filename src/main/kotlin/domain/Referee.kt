package domain

class Referee(private val dealerScore: Score, private val userScore: List<Score>) {

    fun getResult(): List<GameResult> {
        return userScore.map { userScore ->
            calculateResult(userScore)
        }
    }

    private fun calculateResult(score: Score): GameResult {
        if (isDraw(score))
            return GameResult.DRAW
        if (isLose(score))
            return GameResult.LOSE
        return GameResult.WIN
    }

    private fun isDraw(score: Score) =
        ((dealerScore.isBurst()) and (score.isBurst())) or (dealerScore.value == score.value)

    private fun isLose(score: Score) =
        ((dealerScore.isOver(score)) and (!dealerScore.isBurst())) or (score.isBurst())
}
