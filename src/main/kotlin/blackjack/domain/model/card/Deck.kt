package blackjack.domain.model.card

class Deck(
    private val _card: ArrayDeque<Card> = ArrayDeque(Card.CARD_INDEX_RANGE.shuffled().map { Card(it) }),
) {
    fun popCard(): Card {
        require(!_card.isEmpty()) { ERROR_EMPTY_DECK }
        return _card.removeFirst()
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "모든 카드가 소진되어 덱이 비었습니다"
    }
}
