package blackjack.domain

class Hand(
    private val onBusted: () -> Unit,
) {
    var cards: List<Card> = listOf()
        private set

    val score: Score
        get() {
            val score = Score(cards)
            if (score is Score.Bust) {
                onBusted()
            }
            return score
        }
    val canHit: Boolean get() = score is Score.Hittable

    fun draw(card: Card) {
        require(canHit) { MESSAGE_CAN_HIT_WHEN_SCORE_UNDER_MAX_SCORE }
        cards = cards.plus(card)
    }

    companion object {
        private const val MESSAGE_CAN_HIT_WHEN_SCORE_UNDER_MAX_SCORE = "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다."
    }
}
