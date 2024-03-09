package blackjack.model

class GameInformation(cards: Set<Card> = emptySet(), state: GameState = GameState.Running.Hit) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    private var _state: GameState = state
    val state: GameState
        get() = _state

    fun drawCard(card: Card) {
        _cards.add(card)
    }

    fun changeState(state: GameState) {
        _state = state
    }
}
