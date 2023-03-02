class Cards(cards: List<Card> = listOf(Card.draw(), Card.draw())) {

    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.size == 2)
    }

    fun draw() {
        _cards.add(Card.draw())
    }
}
