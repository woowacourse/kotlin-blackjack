package blackjack.model.card

const val MINIMUM_CARDS_COUNT = 2

class Hand(val cards: List<Card>) {
    init {
        require(cards.size >= MINIMUM_CARDS_COUNT)
    }
}
