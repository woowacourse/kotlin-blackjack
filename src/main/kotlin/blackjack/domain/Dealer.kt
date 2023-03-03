package blackjack.domain

import blackjack.dto.HandDTO

class Dealer : Player("딜러") {
    fun isStay(): Boolean = hand.calculateTotalScore() >= STAY_SCORE

    fun getFirstCardHand(): HandDTO = HandDTO(name, listOf(hand.cards.first().toString()))

    companion object {
        private const val STAY_SCORE = 17
    }
}
