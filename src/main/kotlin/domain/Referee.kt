package domain

class Referee(private val dealerScore: Int, private val userScore: List<Int>) {

    fun getResult(): List<GameResult> {
        return userScore.map { userScore ->
            calculateResult(userScore)
        }
    }

    private fun calculateResult(score: Int): GameResult {
        if (((dealerScore > 21) and (score > 21)) or (dealerScore == score)) return GameResult.DRAW
        if (((dealerScore > score) and (dealerScore < 22)) or (score > 21)) return GameResult.LOSE
        return GameResult.WIN
    }
}
