package blackjack.view

import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.participant.Participant

interface OutputView {
    fun printFirstDraw(participantsCards: List<ParticipantCards>)
    fun printDraw(participant: Participant)
    fun printResult(results: ParticipantResults)
}
