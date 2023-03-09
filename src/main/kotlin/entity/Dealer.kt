package entity

import model.CardFactory

class Dealer(val cards: Cards = Cards()) : User {
    override fun isDistributable(): Boolean = cards.sumOfNumbers() <= MAXIMUM_CARD_SUM_NUMBER

    fun distribute(cardFactory: CardFactory, printDealerStatus: () -> Unit) {
        if (isDistributable()) {
            cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
            printDealerStatus()
        }
    }

    fun pickFirstCard(): Card {
        return cards.value[0]
    }

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
