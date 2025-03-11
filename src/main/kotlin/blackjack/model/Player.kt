package blackjack.model

class Player(
    name: String,
    scoreCalculator: ScoreCalculator,
) : Participant(name, scoreCalculator) {
    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    override fun isDrawable(): Boolean = !isBust()

    companion object {
        private const val FIRST_SHOWN_COUNT = 2
    }
}
