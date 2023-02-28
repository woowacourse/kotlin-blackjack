class CardPicker(private val cards: List<Card>) {
    private var cursor = 0
    fun pick(): Card {
        return cards[cursor++]
    }
}
