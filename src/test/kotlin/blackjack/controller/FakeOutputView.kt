package blackjack.controller

import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.view.OutputView

class FakeOutputView : OutputView {
    lateinit var firstOpenCards: List<ParticipantCards>
    var dealerHitCount: Int = 0
    lateinit var participantCards: List<ParticipantCards>
    lateinit var totalScores: List<ParticipantScore>
    lateinit var results: ParticipantResults

    override fun printFirstDraw(participantsCards: List<ParticipantCards>) {
        firstOpenCards = participantsCards
    }

    override fun printDraw(participant: Participant) {
        if (participant is Dealer) dealerHitCount++
    }

    override fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: ParticipantResults
    ) {
        participantCards = cards
        this.totalScores = totalScores
        this.results = results
    }
}
