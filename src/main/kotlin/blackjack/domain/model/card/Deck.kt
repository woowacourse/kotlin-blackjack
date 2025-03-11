package blackjack.domain.model.card

class Deck {
    companion object {
        private val cards = ArrayDeque(Card.CARD_INDEX_RANGE.shuffled().map { Card(it) })

        fun giveCard(): Card {
            require(!cards.isEmpty()) { ERROR_EMPTY_DECK }
            return cards.removeLast()
        }

        private const val ERROR_EMPTY_DECK = "모든 카드가 소진되어 덱이 비었습니다"
    }
}
