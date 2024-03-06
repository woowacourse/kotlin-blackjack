package blackjack.model

abstract class Role {
    abstract val burstCondition: Int
    private val scoreBoard = ScoreBoard()

    fun receiveCard(card: Card) {
        scoreBoard.add(card)
    }

    fun optimizeCardSum() = scoreBoard.optimizeCardSum(burstCondition)

    fun isBurst() = scoreBoard.cardSum >= burstCondition

    fun getCardSum() = scoreBoard.cardSum

    fun decideGameResult(orderRole: Role): GameResult {
        if (scoreBoard.cardSum > orderRole.scoreBoard.cardSum) return GameResult.WIN
        if (scoreBoard.cardSum == orderRole.scoreBoard.cardSum) return GameResult.DRAW
        return GameResult.LOSE
    }
}
