package blackjack.domain

class Hand(
    private val _value: MutableList<Card>,
) {
    val value: List<Card> get() = _value

    /**
     * @return null if score is bigger than 21
     * */
    fun getScore(): Int? {
        val singleValueCards: List<Card> = value.filter { card -> card.hasSingleValue }
        val multipleValueCards: List<Card> = value.filter { card -> !card.hasSingleValue }
        return TODO()
    }

    fun add(card: Card) {
        require(canGetCard()) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        _value.add(card)
    }

    private fun canGetCard(): Boolean = getScore() != null && getScore() != 21

    fun addAll(cards: List<Card>) {
        cards.forEach { card -> add(card) }
    }
}

fun Hand(value: List<Card>): Hand = Hand(value.toMutableList())
