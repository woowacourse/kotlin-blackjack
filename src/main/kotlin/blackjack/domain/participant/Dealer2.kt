package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

class Dealer2 : Participant2() {
    override fun isBust(): Boolean {
        return sumOfCards() + ACE_EXTRACT_SCORE > BUST_STANDARD
    }

    override fun totalScore(): Int =
        if (hasAce() && !isBust()) {
            sumOfCards() + ACE_EXTRACT_SCORE
        } else {
            sumOfCards()
        }

    fun isOverMaxScore(): Boolean {
        if (hasAce() && !isBust()) {
            return sumOfCards() + ACE_EXTRACT_SCORE > DEALER_MAX_SCORE
        }
        return sumOfCards() > DEALER_MAX_SCORE
    }

    companion object {
        const val DEALER_MAX_SCORE = 16
        const val ACE_EXTRACT_SCORE = 10
    }
}
