package blackjack.model

import blackjack.model.CardsStatus.Companion.BUST_SCORE

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards(mutableListOf()),
) : Participant(name, cards) {
    val profit: Money = Money(0)

    private var _results: MutableMap<GameResult, Int> = mutableMapOf()
    val results: Map<GameResult, Int> get() = _results.toMap()

    fun isHit(): Boolean {
        val dealerScore = cards.calculateScore()
        return dealerScore <= 16
    }

    fun getResult(playerScore: Int): GameResult {
        if (playerScore == BUST_SCORE) {
            val result: GameResult = GameResult.WIN
            _results[result] = _results.getOrDefault(result, 0) + 1
            return result
        }
        if (isBust()) {
            val result: GameResult = GameResult.LOSE
            _results[result] = _results.getOrDefault(result, 0) + 1
            return result
        }
        return calculateResult(playerScore)
    }

    private fun calculateResult(playerScore: Int): GameResult {
        val dealerScore: Int = cards.calculateScore()
        val result: GameResult = GameResult.of(dealerScore, playerScore)
        _results[result] = _results.getOrDefault(result, 0) + 1
        return result
    }

    override fun gainMoney(money: Money) {
        profit.plus(money)
    }

    override fun lossMoney(money: Money) {
        profit.minus(money)
    }
}
