package blackjack.model.participant

import blackjack.model.card.Card

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isMoreCard() = score < DEALER_MORE_CARD_MINIMUM

    override fun getInitialCard(): List<Card> = listOf(cards.first())

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_MORE_CARD_MINIMUM = 17
    }
}
