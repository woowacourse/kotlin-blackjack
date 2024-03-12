package blackjack.model.participant.state

import blackjack.util.CompetitionResult

class Blackjack : ParticipantState {
    override fun getResult(opponentScore: Int): CompetitionResult {
        return if (opponentScore == 21) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.WIN
        }
    }
}
