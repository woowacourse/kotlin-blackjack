package domain.result

import domain.card.Hand
import domain.constant.BlackJackConstants

class Score private constructor(val value: Int) {

    fun isSame(other: Score) = value == other.value

    fun isBiggerThan(other: Score) = value > other.value

    companion object {
        private const val ACE_PLUS_POINT = 10
        fun of(hand: Hand): Score {
            val total = hand.getTotalCards()
            return Score(getScoreWithAce(hand, total))
        }

        private fun getScoreWithAce(hand: Hand, total: Int): Int {
            if (!hand.hasAce()) return total
            if (total + ACE_PLUS_POINT > BlackJackConstants.BLACK_JACK_NUMBER) return total
            return total + ACE_PLUS_POINT
        }
    }
}
