package entity

abstract class User(val cards: Cards) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum(): Int {
        return cards.sumOfNumbers()
    }

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
