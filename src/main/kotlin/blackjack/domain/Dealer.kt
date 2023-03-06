package blackjack.domain

import blackjack.dto.HandDTO

class Dealer : Participant(DEALER_NAME) {
    override fun canDraw(): Boolean = hand.calculateTotalScore() >= STAY_SCORE

    fun getFirstCardHand(): HandDTO = HandDTO(name, listOf(hand.cards.first().toString()))

    companion object {
        private const val STAY_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
