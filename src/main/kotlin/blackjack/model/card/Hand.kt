package blackjack.model.card

class Hand(val cards: List<Card>) {
    init {
        require(cards.size >= MINIMUM_CARDS_COUNT)
    }

    fun draw(card: Card): Hand {
        return Hand(cards.plus(card))
    }

    companion object {
        const val MINIMUM_CARDS_COUNT = 2
    }
}
