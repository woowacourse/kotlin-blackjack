package blackjack.model.participant.state

import blackjack.model.participant.CompetitionResult

class Normal(private val myScore: Int) : ParticipantState {
    override fun getResult(opponentScore: Int): CompetitionResult {
        return when {
            opponentScore > 21 -> CompetitionResult.WIN
            opponentScore > myScore -> CompetitionResult.LOSE
            opponentScore < myScore -> CompetitionResult.WIN
            else -> {
                CompetitionResult.SAME
            }
        }
    }
}
