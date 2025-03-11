package blackjack.model

class Player(
    name: String,
    scoreCalculator: ScoreCalculator,
) : Participant(name, scoreCalculator) {
    override fun recieveCards(getCards: (Int) -> List<Card>): Boolean {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(getCards(count))
        return !isBust()
    }

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    companion object {
        private const val FIRST_SHOWN_COUNT = 2
    }
}
