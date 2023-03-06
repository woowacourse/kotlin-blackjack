package domain

class RandomCardPicker : CardDrawer {
    private val cards =
        CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card(cardCategory, it) } }

    val size: Int
        get() = cards.size

    fun contains(card: Card): Boolean {
        return cards.contains(card)
    }

    override fun draw(): Card {
        return cards.shuffled()[0]
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() })
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
    }
}
