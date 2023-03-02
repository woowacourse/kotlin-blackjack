class Cards(cards: List<Card> = listOf(Card.draw(), Card.draw())) {

    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.size == 2)
    }

    fun draw(card: Card = Card.draw()) {
        _cards.add(card)
    }

    fun getSum(): Int {
        val count = cards.count { card -> card.number == CardNumber.A }
        var sum = cards.sumOf { card -> card.number.value }
        // TODO: depth 개선
        repeat(count) {
            sum += 10
            if (sum > 21)
                return sum - 10
        }

        return sum
    }
}
