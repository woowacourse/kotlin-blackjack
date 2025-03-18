package blackjack.domain.participants

import blackjack.domain.card.Card

abstract class Participant(initialHand: List<Card> = emptyList()) {
    val hand: Hand = Hand(initialHand)

    val score: Int
        get() = hand.calculateScore()

    abstract fun canHit(): Boolean

    abstract fun visibleCard(isFirstTurn: Boolean): List<Card>

    fun addCard(card: Card) = hand.addCard(card)

    fun isBlackjack(): Boolean = hand.isBlackjack()

    fun isBust(): Boolean = hand.isBust()

    fun getDrawAmount(): Int {
        if (hand.cards.isEmpty()) {
            return FIRST_TURN_DRAW_AMOUNT
        }

        if (canHit()) {
            return HIT_DRAW_AMOUNT
        }

        return 0
    }

    companion object {
        private const val FIRST_TURN_DRAW_AMOUNT = 2
        private const val HIT_DRAW_AMOUNT = 1
    }
}
