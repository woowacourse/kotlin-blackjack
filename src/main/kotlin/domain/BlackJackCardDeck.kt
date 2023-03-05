package domain

class BlackJackCardDeck : CardDeck {
    val size: Int
        get() = CARD_DECK.size

    override fun draw(): Card {
        return CARD_DECK.shuffled()[0]
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() }.toSet())
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
        private val CARD_DECK: List<Card>

        init {
            val cards = mutableListOf<Card>()
            CardCategory.values().forEach { cardCategory ->
                CardNumber.values().forEach { cardNumber ->
                    cards.add(Card(cardCategory, cardNumber))
                }
            }
            CARD_DECK = cards.toList()
        }
    }
}
