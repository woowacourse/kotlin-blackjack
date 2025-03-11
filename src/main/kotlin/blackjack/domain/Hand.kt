package blackjack.domain

class Hand(
    private val onBusted: () -> Unit,
) {
    private val _cards = Cards()
    val cards: List<Card> = _cards.value

    val score: Int
        get() =
            _cards.score ?: run {
                onBusted()
                SCORE_BUSTED
            }

    fun draw(card: Card) {
        _cards.add(card)
    }

    companion object {
        private const val SCORE_BUSTED = -1
    }
}
