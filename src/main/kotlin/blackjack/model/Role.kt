package blackjack.model

abstract class Role {
    private val burstCondition = 21
    private val scoreBoard = ScoreBoard()

    fun receiveCard(card: Card) {
        scoreBoard.add(card)
    }

    fun optimizeCardSum() = scoreBoard.optimizeCardSum(burstCondition)

    fun isBurst() = scoreBoard.cardSum >= burstCondition

    fun getCardSum() = scoreBoard.cardSum

    fun getCards() = scoreBoard.handCards.cards

    fun decideGameResult(orderRole: Role): GameResult {
        return when {
            isBurst() -> GameResult.LOSE
            orderRole.isBurst() -> GameResult.WIN
            scoreBoard.cardSum > orderRole.scoreBoard.cardSum -> GameResult.WIN
            scoreBoard.cardSum == orderRole.scoreBoard.cardSum -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }
}
