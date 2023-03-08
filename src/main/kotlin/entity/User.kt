package entity

abstract class User(val cards: Cards) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum(): Int {
        return cards.sumOfNumbers()
    }

    fun addCards(cards: Cards) {
        this.cards.addCards(cards)
    }

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
