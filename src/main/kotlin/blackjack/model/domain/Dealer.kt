package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.THRESHOLD

class Dealer(override val name: String = DEALER_NAME) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var status: Status = Status.None

    fun drawUntilThreshold(cards: Deck): Int {
        var count: Int = 0
        while (sumCardNumber <= THRESHOLD) {
            receiveCard(cards.spreadCard())
            count++
        }
        return count
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}
