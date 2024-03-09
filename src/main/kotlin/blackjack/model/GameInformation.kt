package blackjack.model

class GameInformation(cards: Set<Card> = emptySet(), val state: GameState = GameState.Running.Hit) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun drawCard(card: Card) {
        _cards.add(card)
    }
}
