package blackjack.domain.model

import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerBetInfo

class Scoreboard(
    private val participants: Participants,
) {
    fun getDealerProfit(playerBetInfos: List<PlayerBetInfo>): Double {
        val playersProfit: Collection<Double> = getPlayersProfit(playerBetInfos).values
        return -playersProfit.sum()
    }

    fun getPlayersProfit(playerBetInfos: List<PlayerBetInfo>): Map<Player, Double> {
        val maps: List<Map<Player, Double>> = playerBetInfos.map { it.profit(participants.dealer) }
        return maps.reduce { acc, map -> acc + map }
    }

    fun getDealerResult(): Map<GameResult, Int> {
        val dealerResult: List<GameResult> = participants.players.map { player -> participants.dealer.compareTo(player) }
        val initResult: Map<GameResult, Int> = GameResult.entries.associateWith { 0 }
        return initResult + dealerResult.groupingBy { it }.eachCount()
    }
}
