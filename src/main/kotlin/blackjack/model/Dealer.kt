package blackjack.model

class Dealer(
    scoreCalculator: ScoreCalculator,
) : Participant(scoreCalculator) {
    override fun recieveCards(getCards: (Int) -> List<Card>): Boolean {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(getCards(count))
        return score() <= DRAW_CRITERIA && !isBust()
    }

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    fun additionalDrawCount(): Int = cards.size - INITIAL_DRAW_COUNT

    companion object {
        private const val DRAW_CRITERIA = 16
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
        private const val FIRST_SHOWN_COUNT = 1
    }
}
