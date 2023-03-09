package model.participants

import model.cards.CardPack
import model.cards.Hand

abstract class Participant(val hand: Hand, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean = hand.sum() > BLACKJACK_POINT

    fun isBlackJack(): Boolean = (hand.size == 2) && (hand.sum() == BLACKJACK_POINT)

    fun pick(cardPack: CardPack) {
        hand.add(cardPack.pop())
    }

    companion object {
        const val BLACKJACK_POINT = 21
    }
}
