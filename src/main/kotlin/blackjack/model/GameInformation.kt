package blackjack.model

class GameInformation(cards: Set<Card> = emptySet(), state: GameState = GameState.Running.HIT) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    private var _state: GameState = state
    val state: GameState
        get() = _state

    fun drawCard(card: Card) {
        _cards.add(card)
        judgeState()
    }

    fun changeStateToStay() {
        _state = GameState.Finished.STAY
    }

    private fun judgeState() {
        _state =
            when {
                ScoreCalculator.calculateScore(cards) > BLACKJACK_SCORE -> GameState.Finished.BUST
                ScoreCalculator.calculateScore(cards) == BLACKJACK_SCORE && cards.size == BLACKJACK_CARD_SIZE ->
                    GameState.Finished.BLACKJACK
                else -> GameState.Running.HIT
            }
    }

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val BLACKJACK_SCORE = 21
    }
}
