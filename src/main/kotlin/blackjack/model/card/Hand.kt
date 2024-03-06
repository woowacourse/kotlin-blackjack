package blackjack.model.card

class Hand(val cards: MutableList<Card>) {
    init {
        require(cards.size >= MINIMUM_CARDS_COUNT) { "손패는 2장 이상이어야 합니다." }
    }

    fun draw(card: Card) {
        cards.add(card)
    }

    companion object {
        const val MINIMUM_CARDS_COUNT = 2
    }
}
