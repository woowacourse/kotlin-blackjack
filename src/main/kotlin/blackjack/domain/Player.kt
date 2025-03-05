package blackjack.domain

import blackjack.domain.enums.Result

class Player(
    private val name: String,
    val hand: Hand,
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int = hand.calculateScore()

    fun isBust(): Boolean = hand.isBust()

    fun getResult(dealerScore: Int): Result {
        val playerScore = calculateScore()
        if (isBust() || playerScore < dealerScore) {
            return Result.LOSE
        }
        return Result.WIN
    }
}
