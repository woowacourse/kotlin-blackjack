package blackjack.model

import blackjack.model.CardsStatus.Companion.BUST_SCORE

class Dealer(
    name: String = "딜러",
    hand: Hand = Hand(mutableListOf()),
) : Participant(name, hand) {
    val profit: Money = Money(0.0)

    fun isHit(): Boolean {
        val dealerScore = hand.calculateScore()
        return dealerScore <= DEALER_HIT_SCORE
    }

    fun getResult(playerScore: Int): GameResult {
        if (playerScore == BUST_SCORE) return GameResult.WIN
        if (isBust()) return GameResult.LOSE
        return calculateResult(playerScore)
    }

    private fun calculateResult(playerScore: Int): GameResult = GameResult.of(getScore(), playerScore)

    override fun gainMoney(money: Money) {
        profit.plus(money)
    }

    override fun lossMoney(money: Money) {
        profit.minus(money)
    }

    companion object {
        private const val DEALER_HIT_SCORE = 16
    }
}
