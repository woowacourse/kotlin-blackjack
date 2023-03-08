package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun canDraw(): Boolean

    infix fun judge(other: Participant): GameResult = when {
        isBust() && other.isBust() -> GameResult.DRAW
        isBust() -> GameResult.LOSE
        other.isBust() -> GameResult.WIN
        getTotalScore() == other.getTotalScore() -> GameResult.DRAW
        getTotalScore() > other.getTotalScore() -> GameResult.WIN
        else -> GameResult.LOSE
    }

    fun isBust(): Boolean = cards.isOverBlackjack(getTotalScore())

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int = cards.calculateTotalScore()

    fun getCards(): List<Card> = cards.items
}
