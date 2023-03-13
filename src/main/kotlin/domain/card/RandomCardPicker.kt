package domain.card

class RandomCardPicker : CardPicker {
    private val cardDeck =
        CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card.of(cardCategory, it) } }

    override fun draw(): Card {
        return cardDeck.shuffled().first()
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
    }
}
