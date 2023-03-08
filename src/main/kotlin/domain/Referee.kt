package domain

class Referee(private val dealerScore: Score) {

    fun getResult(users: List<User>): List<GameResult> {
        return users.map { user ->
            user.gameResult = calculateResult(Score.valueOf(user.cards.actualCardValueSum()))
            user.gameResult
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
