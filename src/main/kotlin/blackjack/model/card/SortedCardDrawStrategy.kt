package blackjack.model.card

object SortedCardDrawStrategy : CardDrawStrategy {
    private val sortedDeck: List<Card> = CardDeck.cardDeck.toList()
    private var cardIndex = 0

    override fun drawCard(): Card {
        val card = runCatching { sortedDeck[cardIndex++] }
        return card.getOrElse {
            resetCardDeck()
            drawCard()
        }
    }

    override fun resetCardDeck() {
        cardIndex = 0
    }
}
