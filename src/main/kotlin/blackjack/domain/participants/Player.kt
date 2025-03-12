package blackjack.domain.participants

import blackjack.const.GameRule

class Player(val name: String) : Participant() {
    override fun canHit(): Boolean = score() <= GameRule.BLACKJACK_SCORE
}
