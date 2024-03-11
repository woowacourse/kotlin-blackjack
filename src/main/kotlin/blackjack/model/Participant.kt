package blackjack.model

abstract class Participant(val name: String, private val scorePolicy: ScorePolicy) {
    protected val cards = Cards()
    private val score: Int
        get() = scorePolicy.score(cards)

    fun isBusted(): Boolean = cards.isBusted()

    fun isMaxScore(): Boolean = cards.isMaxScore()

    fun addCard(card: Card) = cards.addCard(card)

    fun getCardSum(): Int = cards.sum()

    fun showCard() = cards.showCard()

    abstract fun isHitable(): Boolean

    infix fun versus(other: Participant): GameResult =
        when {
            this.score > other.score -> GameResult.Win
            this.score == other.score -> GameResult.Draw
            else -> GameResult.Lose
        }
}
