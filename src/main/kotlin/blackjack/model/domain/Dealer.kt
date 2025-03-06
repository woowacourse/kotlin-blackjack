package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.THRESHOLD

class Dealer(val name: String = "딜러") : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var alive: Boolean = true

    fun drawUntilThreshold(cards: Deck) {
        while (sumCardNumber <= THRESHOLD) {
            receiveCard(cards.spreadCard())
        }
    }
}
