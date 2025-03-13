package blackjack.domain

class Hand(
    private val onBusted: () -> Unit,
) {
    var cards: List<Card> = listOf()
        private set

    val score: Score
        get() {
            val score: Score = Score(cards)
            if (score is Score.Bust) {
                onBusted()
            }
            return score
        }

    fun draw(card: Card) {
        cards = cards.plus(card)
    }

    fun add(card: Card) {
        require(score is Score.Hittable && score.value < 21) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        cards = cards.plus(card)
    }
}
