package blackjack.domain

class Hand {
    private val _cards = Cards()
    val cards: List<Card> = _cards.value

    fun getScore(onBusted: () -> Unit): Score {
        if (_cards.score is Score.Bust) {
            onBusted()
        }
        return _cards.score
    }

    fun draw(card: Card) {
        _cards.add(card)
    }
}
