package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

class Player(
    val name: String,
) : Participant() {
    override fun isBust(): Boolean = sumOfCards() > BUST_STANDARD

    override fun totalScore(): Int {
        return if (hasAce() && !isBustByMaxAce()) {
            sumOfCards() + ACE_EXTRACT_SCORE
        } else {
            sumOfCards()
        }
    }

    private fun isBustByMaxAce(): Boolean = sumOfCards() + ACE_EXTRACT_SCORE > BUST_STANDARD
}
