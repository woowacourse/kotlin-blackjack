package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

class Dealer : Participant() {
    override fun totalScore(): Int {
        val baseScore = sumOfCards()
        return if (hasAce() && baseScore + ACE_EXTRACT_SCORE <= BUST_STANDARD) {
            baseScore + ACE_EXTRACT_SCORE
        } else {
            baseScore
        }
    }

    fun isOverMaxScore(): Boolean {
        if (hasAce() && !isBust()) {
            return sumOfCards() + ACE_EXTRACT_SCORE > DEALER_MAX_SCORE
        }
        return sumOfCards() > DEALER_MAX_SCORE
    }
}
