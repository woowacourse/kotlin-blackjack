package domain.person

import constant.BlackJackRule.DEALER_STAND_CONDITION
import domain.card.Card
import domain.card.HandOfCards
import domain.card.strategy.GetAppropriateSum

class Dealer(
    override val handOfCards: HandOfCards,
) : Person() {
    override val name: String = DEALER

    fun showFirstCard(): List<Card> = handOfCards.showFirstCard()

    override fun canReceiveMoreCard(): Boolean = getTotalCardNumber(GetAppropriateSum()) <= DEALER_STAND_CONDITION

    companion object {
        const val DEALER = "딜러"
    }
}
