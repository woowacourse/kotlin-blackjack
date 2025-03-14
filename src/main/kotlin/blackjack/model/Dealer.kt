package blackjack.model

import blackjack.model.CardsStatus.Companion.BUST_SCORE

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards(mutableListOf()),
) : Participant(name, cards) {
    val profit: Money = Money(0)

    fun isHit(): Boolean {
        val dealerScore = cards.calculateScore()
        return dealerScore <= 16
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
}
