package blackjack.model

class DealingShoe(cards: List<Card>) {
    private val cards = cards.toList()
    private var idx = 0

    fun pickCard(): Card {
        if (idx == cards.size) {
            throw IllegalStateException(EXCEPTION_EMPTY_CARD_MESSAGE)
        }
        return cards[idx++]
    }

    companion object {
        const val EXCEPTION_EMPTY_CARD_MESSAGE = "모든 카드를 소진해 게임을 종료합니다."
    }
}
