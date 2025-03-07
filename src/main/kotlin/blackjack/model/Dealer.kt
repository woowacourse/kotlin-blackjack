package blackjack.model

class Dealer(
    private val dealerName: String = "딜러",
    override val cards: Cards = Cards(mutableListOf()),
) : Player(dealerName) {
    private var _results: MutableMap<GameResult, Int> = mutableMapOf()
    val results: Map<GameResult, Int> get() = _results.toMap()

    override fun appendCard(card: Card) {
        cards.add(card)
    }

    fun updateResult(playerScore: Int): GameResult {
        val dealerScore: Int = cards.calculateScore()
        val result: GameResult = GameResult.of(dealerScore, playerScore)
        _results[result] = _results.getOrDefault(result, 0) + 1
        return result
    }
}
