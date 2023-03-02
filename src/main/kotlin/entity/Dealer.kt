package entity

import model.CardDistributor

class Dealer(cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() <= MAXIMUM_CARD_SUM_NUMBER

    fun requestReceiveMoreCard(cardDistributor: CardDistributor) {
        if (isDistributable()) {
            cards.addCards(cardDistributor.distribute(SINGLE_DISTRIBUTE_COUNT))
        }
    }

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
