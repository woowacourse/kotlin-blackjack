package domain

class RandomCardPicker : CardDrawer {
    override fun draw(): Card {
        val randomCardCategory = CardCategory.values().random()
        val randomCardNumber = CardNumber.values().random()
        return Card.of(cardCategory = randomCardCategory, cardNumber = randomCardNumber)
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() })
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
    }
}
