package blackjack.model

class Dealer(
    scoreCalculator: ScoreCalculator,
) : Participant(scoreCalculator) {
    fun drawIfNeeded(cardDeck: CardDeck): Int {
        var count = INITIAL_RESULT_COUNT

        while (score() <= DEALER_DRAW_CRITERIA && !isBust()) {
            draw(cardDeck)
            count++
        }

        return count
    }

    fun firstVisibleCard(): List<Card> = cards.take(DEALER_FIRST_SHOWN_COUNT)

    companion object {
        private const val INITIAL_RESULT_COUNT = 0
        private const val DEALER_DRAW_CRITERIA = 16
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
