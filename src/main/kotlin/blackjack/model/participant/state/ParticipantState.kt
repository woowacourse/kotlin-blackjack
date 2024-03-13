package blackjack.model.participant.state

import blackjack.model.participant.CompetitionResult

fun interface ParticipantState {
    fun getResult(opponentScore: Int): CompetitionResult
}
