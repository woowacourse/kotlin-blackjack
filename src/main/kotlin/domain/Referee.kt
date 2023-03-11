package domain

class Referee(private val dealerScore: Score) {

    fun getResult(users: List<User>): List<GameResult> {
        return users.map { user ->
            user.setGameResult(calculateResult(user))
            user.gameResult
        }
    }

    private fun calculateResult(user: User): GameResult {
        if (isLose(user.cards.score))
            return GameResult.LOSE
        if (isDraw(user.cards.score) && !user.cards.isBlackJack())
            return GameResult.DRAW
        return GameResult.WIN
    }

    private fun isDraw(score: Score) =
        ((dealerScore.isBust()) and (score.isBust())) or (dealerScore.value == score.value)

    private fun isLose(score: Score) =
        ((dealerScore.isOver(score)) and (!dealerScore.isBust())) or (score.isBust())
}
