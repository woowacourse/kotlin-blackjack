package blackjack.domain.model

class Deck {
    companion object {
        private val cards = ArrayDeque(Card.CARD_INDEX_RANGE.shuffled().map { Card(it) })

        fun pop(): Card {
            return cards.removeLast()
            // todo(카드 52장 소진시의 예외처리 추가)
        }
    }
}
