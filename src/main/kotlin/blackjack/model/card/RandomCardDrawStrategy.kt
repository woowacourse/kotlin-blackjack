package blackjack.model.card

object RandomCardDrawStrategy : CardDrawStrategy {
    private var shuffleDeck: List<Card> = CardDeck.cardDeck.shuffled().toList()
    private var cardIndex = 0

    override fun drawCard(): Card {
        val card = runCatching { shuffleDeck[cardIndex++] }
        return card.getOrElse {
            resetCardDeck()
            drawCard()
        }
    }

    override fun resetCardDeck() {
        cardIndex = 0
        shuffleDeck = shuffleDeck.shuffled()
    }
}
