package blackjack.model

abstract class Participant(
    val name: String,
    private val strengthPolicy: StrengthPolicy,
) {
    protected val cards = Cards()
    private val strength: Int
        get() = strengthPolicy.strength(cards)

    fun isBusted(): Boolean = cards.isBusted()

    fun isMaxScore(): Boolean = cards.isMaxScore()

    fun addCard(card: Card) = cards.addCard(card)

    fun getCardSum(): Int = cards.sum()

    fun showCard() = cards.toList()

    abstract fun showInitialCard(): List<Card>

    infix fun pick(deck: Deck) {
        this.addCard(deck.pop())
    }

    fun isNotBustedAndHitable(): Boolean = !isBusted() && isHitable()

    protected abstract fun isHitable(): Boolean

    infix fun versus(other: Participant): GameResult =
        when {
            this.strength > other.strength -> GameResult.Win
            this.strength == other.strength -> GameResult.Draw
            else -> GameResult.Lose
        }
}
