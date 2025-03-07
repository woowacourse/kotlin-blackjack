package blackjack.model.domain

import blackjack.model.service.Blackjack.Companion.THRESHOLD

class Dealer(override val name: String = DEALER_NAME) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var status: Status = Status.None

    fun overThreshold(): Boolean {
        return sumCardNumber > THRESHOLD
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}
