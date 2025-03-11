package blackjack.model.participant

import blackjack.model.ResultCalculator.BLACKJACK_NUMBER
import blackjack.model.card.Card

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isMoreCard() = adjustScore < DEALER_MORE_CARD_MINIMUM

    override fun getInitialCard(): List<Card> = listOf(cards.first())

    override fun isBust(): Boolean = adjustScore > BLACKJACK_NUMBER

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_MORE_CARD_MINIMUM = 17
    }
}
