package domain.card

class RandomCardPicker : CardPicker {
    private val cardDeck =
        CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card.of(cardCategory, it) } }

    override fun draw(): Card {
        return cardDeck.shuffled()[FIRST]
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() })
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
        private const val FIRST = 1
    }
}
