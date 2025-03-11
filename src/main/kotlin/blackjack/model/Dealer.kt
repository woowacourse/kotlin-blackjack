package blackjack.model

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards(mutableListOf()),
) : Participant(name, cards) {
    private var _results: MutableMap<GameResult, Int> = mutableMapOf()
    val results: Map<GameResult, Int> get() = _results.toMap()

    fun isHit(): Boolean {
        val dealerScore = cards.calculateScore()
        return PlayerBehavior.from(dealerScore) == PlayerBehavior.HIT
    }

    fun updateResult(playerScore: Int): GameResult {
        val dealerScore: Int = cards.calculateScore()
        val result: GameResult = GameResult.of(dealerScore, playerScore)
        _results[result] = _results.getOrDefault(result, 0) + 1
        return result
    }
}
