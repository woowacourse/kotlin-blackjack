package blackjack.model

class GameInformation(cards: Set<Card> = emptySet(), state: GameState = GameState.Running.READY) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    private var _state: GameState = state
    val state: GameState
        get() = _state

    private var _score: Int = 0
    val score: Int
        get() = _score

    fun drawCard(card: Card) {
        _cards.add(card)
        _score = ScoreCalculator.calculateScore(cards)
        judgeState()
    }

    fun changeState(state: GameState) {
        _state = state
    }

    private fun judgeState() {
        if (isBust()) changeState(GameState.Finished.BUST)
        if (isBlackJack()) changeState(GameState.Finished.BLACKJACK)
    }

    private fun isBust(): Boolean {
        return score > BLACKJACK_SCORE
    }

    private fun isBlackJack(): Boolean {
        return score == BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
