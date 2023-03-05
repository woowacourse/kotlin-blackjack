package domain

class BlackJackCardDeck : CardDeck {

    override fun draw(): Card {
        return CARD_DECK.shuffled()[0]
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() })
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
        private val CARD_DECK: List<Card>

        init {
            CARD_DECK =
                CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card(cardCategory, it) } }
        }
    }
}
