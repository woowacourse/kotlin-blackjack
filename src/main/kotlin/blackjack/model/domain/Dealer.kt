package blackjack.model.domain

import blackjack.model.domain.Blackjack.Companion.THRESHOLD

class Dealer(val name: String = "딜러") : Participants {
    override val cards: MutableList<Card> = mutableListOf()

    fun drawUntilThreshold(cards: Deck) {
        while (sumCardNumber <= THRESHOLD) {
            receiveCard(cards.spreadCard())
        }
    }
}
