package blackjack.model

abstract class Participant(
    val name: String,
    private val strengthPolicy: StrengthPolicy,
) {
    protected val cards = Cards()
    private val strength: Int
        get() = strengthPolicy.strength(cards)

    fun isBlackjack(): Boolean = cards.isBlackJack()

    fun isBusted(): Boolean = cards.isBusted()

    fun isMaxScore(): Boolean = cards.isMaxScore()

    fun addCard(card: Card) = cards.addCard(card)

    fun getCardSum(): Int = cards.scoreSum()

    fun cardsList() = cards.toList()

    abstract fun initialCardsList(): List<Card>

    fun isNotBustedAndHitable(): Boolean = !isBusted() && isHitable()

    protected abstract fun isHitable(): Boolean

    infix fun versus(other: Participant): GameResult =
        when {
            this.strength > other.strength -> GameResult.Win
            this.strength == other.strength -> GameResult.Draw
            else -> GameResult.Lose
        }
}
