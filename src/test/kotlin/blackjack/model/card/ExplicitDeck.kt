package blackjack.model.card

class ExplicitDeck : CardDeck {
    private val explicitCardDeck =
        listOf(
            Card(Suit.CLOVER, Denomination.FIVE),
            Card(Suit.DIAMOND, Denomination.THREE),
            Card(Suit.HEART, Denomination.SEVEN),
            Card(Suit.SPADE, Denomination.SIX),
            Card(Suit.DIAMOND, Denomination.FOUR),
            Card(Suit.CLOVER, Denomination.EIGHT),
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.HEART, Denomination.NINE),
        )

    private var cardIndex = 0

    override fun draw(): Card {
        if (cardIndex == explicitCardDeck.size) {
            cardIndex = 0
        }
        return explicitCardDeck[cardIndex++]
    }
}
