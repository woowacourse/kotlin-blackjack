class Cards(cards: List<Card>) {

    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.size == 2)
    }
}
