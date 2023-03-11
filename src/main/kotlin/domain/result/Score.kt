package domain.result

import domain.card.Hand
import domain.constant.BlackJackConstants

class Score private constructor(val value: Int) {

    fun isSame(other: Int) = value == other

    fun isBiggerThan(other: Int) = value > other

    companion object {
        fun of(hand: Hand): Score {
            val total = hand.getTotalCards()
            if (!hand.hasAce) return Score(total)
            if (total + 10 > BlackJackConstants.BLACK_JACK_NUMBER) return Score(total)
            return Score(total + 10)
        }
    }
}
