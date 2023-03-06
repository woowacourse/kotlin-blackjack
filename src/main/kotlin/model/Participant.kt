package model

import model.Cards.Companion.PARTICIPANT_STANDARD_BUST_POINT

abstract class Participant(val cards: Cards, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean {
        if (cards.sum() > PARTICIPANT_STANDARD_BUST_POINT) return true
        return false
    }
}
