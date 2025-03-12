package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

class Player(
    val name: String,
) : Participant() {
    override fun isDrawable(): Boolean {
        return totalScore() <= BUST_STANDARD
    }
}
