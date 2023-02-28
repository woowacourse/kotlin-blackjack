class Cards(cards: List<Card>) {

    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.size == 2)
    }

    fun isPossibleToDraw(score: Int): Boolean {
        if (cards.sumOf { card -> card.number.value } >= score)
            return false

        return true
    }

    fun getSum(): Int {
        return cards.sumOf { card -> card.number.value }
    }
}
