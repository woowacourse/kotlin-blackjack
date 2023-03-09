package entity.users

import entity.card.Cards

abstract class User(val cards: Cards) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum() = cards.sumOfNumbers()

    fun addCards(cards: Cards) = this.cards.addCards(cards)

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
