package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Hand

class Dealer(override val name: String = DEALER_NAME) : Participants() {
    override val hand: Hand = Hand(mutableListOf())
    override var status: GameResult = GameResult.None

    override fun canHit(): Boolean {
        return sumCardNumber <= THRESHOLD
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val THRESHOLD: Int = 16
    }
}
