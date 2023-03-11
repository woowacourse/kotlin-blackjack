package blackjack.controller

import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.view.OutputView

class FakeOutputView : OutputView {
    lateinit var firstOpenCards: List<ParticipantCards>
    var dealerHitCount: Int = 0
    // lateinit var participantCards: List<Pair<String, List<Card>>>
    // lateinit var totalScores: List<Pair<String, Int>>
    lateinit var dealerResult: DealerResult
    lateinit var playerResults: List<PlayerResult>

    override fun printFirstDraw(participantsCards: List<ParticipantCards>) {
        firstOpenCards = participantsCards
    }

    override fun printDraw(participant: Participant) {
        if (participant is Dealer) dealerHitCount++
    }

    override fun printResult(
        results: ParticipantResults
    ) {
        // participantCards = results.dealerResult.cards + results.playerResults.map { it.cards }
        // this.totalScores = totalScores
        // this.results = results
        dealerResult = results.dealerResult
        playerResults = results.playerResults
    }
}
