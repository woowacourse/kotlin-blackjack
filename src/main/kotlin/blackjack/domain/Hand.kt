package blackjack.domain

class Hand(
    private val _value: MutableList<Card>,
) {
    val value: List<Card> get() = _value

    /**
     * @return null if score is bigger than 21
     * */
    val score: Int?
        get() {
            if (value.isEmpty()) return 0
            val possibleScores = ScoreCalculator.possibleScoreOf(*(value.toTypedArray()))
            return possibleScores.sortedDescending().firstOrNull { score: Int -> score <= 21 }
        }

    fun draw(card: Card) {
        require(canGetCard()) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        _value.add(card)
    }

    private fun canGetCard(): Boolean = score != null && score != 21
}
