package blackjack.model

abstract class Participant(val name: String, private val strengthPolicy: StrengthPolicy) {
    protected val cards = Cards()
    private val strength: Int
        get() = strengthPolicy.strength(cards)

    fun isBusted(): Boolean = cards.isBusted()

    fun isMaxScore(): Boolean = cards.isMaxScore()

    fun addCard(card: Card) = cards.addCard(card)

    fun getCardSum(): Int = cards.sum()

    fun showCard() = cards.toList()

    fun showFirstCard(): Card {
        return showCard()[0]
    }

    abstract fun isHitable(): Boolean

    infix fun versus(other: Participant): GameResult =
        when {
            this.strength > other.strength -> GameResult.Win
            this.strength == other.strength -> GameResult.Draw
            else -> GameResult.Lose
        }
}
