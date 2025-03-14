package blackjack.domain.model

import blackjack.domain.model.participant.Participants

class Scoreboard(
    private val participants: Participants,
) {
    fun getDealerResult(): Map<GameResult, Int> {
        val dealerResult: List<GameResult> = participants.players.map { player -> participants.dealer.compareTo(player) }
        val initResult: Map<GameResult, Int> = GameResult.entries.associateWith { 0 }
        return initResult + dealerResult.groupingBy { it }.eachCount()
    }
}
