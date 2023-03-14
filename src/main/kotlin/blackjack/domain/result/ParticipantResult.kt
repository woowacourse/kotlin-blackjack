package blackjack.domain.result

import blackjack.domain.player.Participant

class ParticipantResult(val participant: Participant, private val result: Result) {
    fun getProfit(): Int = (participant.betAmount * result.rate).toInt()
}
