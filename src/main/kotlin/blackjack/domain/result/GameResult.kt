package blackjack.domain.result

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants

class GameResult(private val dealer: Dealer, private val participants: Participants) {

    fun getParticipantsResult(): ParticipantsResults {
        val participantsResults = participants.values.map {
            ParticipantResult(it, it.calculateResult(dealer))
        }
        return ParticipantsResults(participantsResults)
    }

    fun getDealerResult(): DealerResult {
        return DealerResult(dealer, -getParticipantsResult().participantsResults.sumOf { it.getProfit() })
    }
}
