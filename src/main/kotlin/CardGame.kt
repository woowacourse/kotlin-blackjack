class CardGame(private val cardPicker: CardPicker) {
    fun pickTwice(): Cards = Cards(
        buildList {
            add(cardPicker.pick())
            add(cardPicker.pick())
        },
    )
}
