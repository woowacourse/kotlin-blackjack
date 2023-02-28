class CardPicker(private val cards: Cards) {
    private var cursor = 0
    fun pick(): Card {
        return cards.cards[cursor++]
    }
}
