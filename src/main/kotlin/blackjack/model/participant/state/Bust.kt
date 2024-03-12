package blackjack.model.participant.state

import blackjack.util.CompetitionResult

class Bust : ParticipantState {
    override fun getResult(opponentScore: Int): CompetitionResult {
        return CompetitionResult.LOSE
    }
}
