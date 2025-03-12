package blackjack.domain

class Cards {
    private val _value: MutableList<Card> = mutableListOf()
    val value: List<Card> get() = _value

    val score: Score get() = Score(value)

    fun add(card: Card) {
        require(score is Score.Hittable && score.value < 21) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        _value.add(card)
    }
}
