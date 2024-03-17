package blackjack.model.participant.testState

import blackjack.model.participant.CompetitionResult

class Blackjack2() : Finish() {
    fun getResult(opponentScore: Int): CompetitionResult {
        return if (opponentScore == 21) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.WIN
        }
    }
}
