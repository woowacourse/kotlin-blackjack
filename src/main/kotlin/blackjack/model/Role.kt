package blackjack.model

abstract class Role {
    abstract val burstCondition: Int
    private val scoreBoard = ScoreBoard()

    fun receiveCard(card: Card) {
        scoreBoard.add(card)
    }

    fun isBurst() = scoreBoard.cardSum >= burstCondition
}
