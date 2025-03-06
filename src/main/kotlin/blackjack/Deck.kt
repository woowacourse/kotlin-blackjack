package blackjack

class Deck {
    private val cards = ArrayDeque(Card.CARD_INDEX_RANGE.shuffled().map { Card(it) })

    fun drawCard(): Card {
        return cards.removeLast()
        // todo(카드 52장 소진시의 예외처리 추가)
    }
}
