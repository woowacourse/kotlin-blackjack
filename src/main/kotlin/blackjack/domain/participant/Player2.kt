package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.participant.Dealer.Companion.ACE_EXTRACT_SCORE

data class Player2(
    val name: String,
) : Participant2() {
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
