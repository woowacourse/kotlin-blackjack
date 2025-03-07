package blackjack.domain

class Hand(
    private val _value: MutableList<Card>,
) {
    val value: List<Card> get() = _value

    fun getSize(): Int = value.size

    /**
     * @return null if all posible score is bigger than 21
     * */
    fun getScore(): Int? {
        val aces: List<Card> = value.filter { card: Card -> card.rank is Ace }
        val acesSums: List<Int> =
            when (aces.size) {
                0 -> listOf(0)
                1 -> listOf(1, 11)
                2 -> listOf(2, 12, 22)
                3 -> listOf(3, 13, 23, 33)
                4 -> listOf(4, 14, 24, 34, 44)
                else -> throw IllegalArgumentException("Cards can't contain more than 4 aces")
            }

        val otherCards: List<Card> = value.filter { card: Card -> card.rank !is Ace }
        val otherCardsSum: Int =
            otherCards.sumOf { card: Card ->
                card.rank.possibleValues.first()
            }
        val possibleSums: List<Int> = acesSums.map { acesSum -> acesSum + otherCardsSum }
        val score: Int? = possibleSums.sortedDescending().firstOrNull { possibleSum -> possibleSum <= 21 }
        return score
    }

    fun add(card: Card) {
        require(canGetCard()) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        _value.add(card)
    }

    private fun canGetCard(): Boolean = getScore() != null && getScore() != 21

    fun add(cards: List<Card>) {
        cards.forEach { card -> add(card) }
    }
}

fun Hand(value: List<Card>): Hand = Hand(value.toMutableList())
