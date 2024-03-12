package blackjack.model

class GameInformation(cards: Set<Card> = emptySet(), state: GameState = GameState.Running.HIT) {
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

    fun changeStateToStay() {
        _state = GameState.Finished.STAY
    }

    private fun judgeState() {
        _state =
            when {
                score > BLACKJACK_SCORE -> GameState.Finished.BUST
                score == BLACKJACK_SCORE -> GameState.Finished.BLACKJACK
                else -> GameState.Running.HIT
            }
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
