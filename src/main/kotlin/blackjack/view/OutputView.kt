package blackjack.view

import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.participant.Participant

interface OutputView {
    fun printFirstOpenCards(participantsCards: List<ParticipantCards>)
    fun printDraw(participant: Participant)
    fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: ParticipantResults
    )
}
