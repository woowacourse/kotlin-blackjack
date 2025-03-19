package blackjack.model

class CardDeck(
    private val cards: List<Card>,
) {
    private var index = 0

    fun pickCard(): Card {
        check(index <= cards.size) { "카드덱보다 더 많은 카드를 출력하려고 합니다" }
        return cards[index++]
    }
}
