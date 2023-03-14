package blackjack.domain.result

import blackjack.domain.player.Participant

class ParticipantResult(val participant: Participant, private val result: Result) {
    fun getProfit(): Int {
        if (participant.isBlackjack) return (participant.betAmount * BLACKJACK_EARNING_RATE).toInt()
        return (participant.betAmount * result.rate).toInt()
    }

    companion object {
        private const val BLACKJACK_EARNING_RATE = 1.5
    }
}
