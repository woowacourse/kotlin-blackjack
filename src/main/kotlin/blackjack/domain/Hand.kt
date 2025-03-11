package blackjack.domain

class Hand {
    private val _cards = Cards()
    val cards: List<Card> = _cards.value

    fun getHandState(onBusted: () -> Unit): HandState {
        if (_cards.state == HandState.Bust) {
            onBusted()
        }
        return _cards.state
    }

    fun draw(card: Card) {
        _cards.add(card)
    }
}
