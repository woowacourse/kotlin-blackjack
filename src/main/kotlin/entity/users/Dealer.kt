package entity.users

import entity.card.Cards

class Dealer(cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() <= MAXIMUM_CARD_SUM_NUMBER

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
