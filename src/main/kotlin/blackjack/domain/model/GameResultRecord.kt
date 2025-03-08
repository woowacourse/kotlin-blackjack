package blackjack.domain.model

import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class GameResultRecord(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun getDealerResult(): Map<GameResult, Int> {
        val dealerResult: List<GameResult> = players.map { player -> dealer.compareTo(player) }
        val initResult = GameResult.entries.associateWith { 0 }
        return initResult + dealerResult.groupingBy { it }.eachCount()
    }
}
