package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.THRESHOLD

class Dealer(override val name: String = DEALER_NAME) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var alive: Boolean = true

    fun drawUntilThreshold(cards: Deck) {
        while (sumCardNumber <= THRESHOLD) {
            receiveCard(cards.spreadCard())
        }
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}
