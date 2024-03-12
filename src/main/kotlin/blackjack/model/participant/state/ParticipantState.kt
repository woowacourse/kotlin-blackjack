package blackjack.model.participant.state

import blackjack.util.CompetitionResult

fun interface ParticipantState {
    fun getResult(opponentScore: Int): CompetitionResult
}
