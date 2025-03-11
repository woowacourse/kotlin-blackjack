package blackjack.domain

class Cards {
    private val _value: MutableList<Card> = mutableListOf()
    val value: List<Card> get() = _value

    val state: HandState get() = HandState(value)

    fun add(card: Card) {
        require(canGetCard()) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        _value.add(card)
    }

    private fun canGetCard(): Boolean = state != HandState.Bust && state != HandState.Blackjack
}
