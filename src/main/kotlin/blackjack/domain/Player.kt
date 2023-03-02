package blackjack.domain

import blackjack.domain.PlayerHand.Companion.SCORE_LIMIT

class Player(val name: String) {
    val hand = PlayerHand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean = hand.calculateTotalScore() > SCORE_LIMIT
}
