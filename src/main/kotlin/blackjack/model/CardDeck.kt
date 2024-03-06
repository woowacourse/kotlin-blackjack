package blackjack.model

class CardDeck {
    private var cards: Set<Card> = Denomination.entries.flatMap { denomination ->
        Suit.entries.map { suit ->
            Card(
                denomination = denomination,
                suit = suit
            )
        }
    }.toSet()

    init {
        cards.shuffled()
    }

    fun draw(): Card {
        val popCard = cards
            .take(DRAW_COUNT)
            .firstOrNull() ?: throw IllegalArgumentException(EMPTY_CARD_DECK)
        cards -= popCard
        return popCard
    }

    companion object {
        private const val DRAW_COUNT: Int = 1
        private const val EMPTY_CARD_DECK = "카드 덱에서 뽑을 수 있는 카드가 없습니다."
        const val MAX_DRAW_COUNT: Int = 52
    }
}
