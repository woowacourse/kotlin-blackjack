package domain.card

class BlackJackCardDeck(private val originalCashedDeck: List<Card> = Card.DECK.toList()) : CardDeck {
    private val deck: MutableList<Card> = originalCashedDeck.shuffled().toMutableList()

    init {
        check(deck.isNotEmpty()) { ERROR_DECK_EMPTY }
    }

    override fun draw(): Card {
        if (deck.isEmpty()) {
            deck.addAll(originalCashedDeck.shuffled())
        }
        return deck.removeLast()
    }

    companion object {
        private const val ERROR_DECK_EMPTY = "[ERROR] 덱에는 최소한 카드가 한 장이상 들어있어야 합니다."
    }
}
