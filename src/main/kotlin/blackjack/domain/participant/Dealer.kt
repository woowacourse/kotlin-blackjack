package blackjack.domain.participant

class Dealer : Participant() {
    fun isOverMaxScore(): Boolean {
        if (hasAce() && !isBust()) {
            return sumOfCards() + ACE_EXTRACT_SCORE > DEALER_MUST_REACH_SCORE
        }
        return sumOfCards() > DEALER_MUST_REACH_SCORE
    }
}
