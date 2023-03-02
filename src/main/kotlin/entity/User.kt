package entity

abstract class User(val cards: Cards) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum(): Int {
        return cards.sumOfNumbers()
    }
}
