class CardGame(private val cardPicker: CardPicker) {
    fun pickTwice(): List<Card> = buildList {
        add(cardPicker.pick())
        add(cardPicker.pick())
    }
}
