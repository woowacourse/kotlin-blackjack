package blackjack.model

class CardDeck {
    private val _cards: MutableSet<Card> = initializeCardDeck()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun pickCard(): Card {
        require(cards.isNotEmpty()) { ERROR_MESSAGE_EMPTY_CARDS }
        val card = _cards.elementAt(FIRST_CARD_INDEX)
        _cards.remove(card)
        return card
    }

    private fun initializeCardDeck(): MutableSet<Card> {
        return CardNumber.entries.flatMap { number -> CardSymbol.entries.map { symbol -> Card(number, symbol) } }
            .shuffled().toMutableSet()
    }

    companion object {
        private const val FIRST_CARD_INDEX = 0
        private const val ERROR_MESSAGE_EMPTY_CARDS = "[ERROR] 더이상 뽑을 카드가 없어 게임을 종료합니다."
    }
}
