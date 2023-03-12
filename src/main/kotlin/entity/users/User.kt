package entity.users

import entity.card.Cards

abstract class User(val userInformation: UserInformation) {
    abstract fun isDistributable(): Boolean
    fun cardsNumberSum() = userInformation.cards.sumOfNumbers()

    fun addCards(cards: Cards) = this.userInformation.cards.addCards(cards)

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
