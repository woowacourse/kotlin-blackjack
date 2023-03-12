package entity.users

import entity.card.Cards

abstract class User(val gameInformation: GameInformation) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum() = gameInformation.cards.sumOfNumbers()

    fun addCards(cards: Cards) = this.gameInformation.cards.addCards(cards)

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
