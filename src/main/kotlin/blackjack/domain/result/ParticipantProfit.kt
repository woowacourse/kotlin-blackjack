package blackjack.domain.result

import blackjack.domain.player.Participant

class ParticipantProfit(val participant: Participant, result: Result) {

    val profit: Int = (participant.betAmount * result.rate).toInt()
}
