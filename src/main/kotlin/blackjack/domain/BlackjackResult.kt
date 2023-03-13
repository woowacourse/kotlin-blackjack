package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players

class BlackjackResult private constructor(private val playersRevenue: Map<Player, Int>) {
    val dealerRevenue: Int = -playersRevenue.values.sum()

    fun getRevenueOf(player: Player): Int? = playersRevenue[player]

    companion object {
        private const val PARTICIPANTS_SHOULD_FINISHED =
            "모든 참여자는 게임이 끝난 상태여야 합니다."

        fun of(dealer: Dealer, players: Players): BlackjackResult {
            require(dealer.isFinished() && players.areFinished()) { PARTICIPANTS_SHOULD_FINISHED }

            val result = createResult(dealer, players)

            return BlackjackResult(result)
        }

        private fun createResult(dealer: Dealer, players: Players): Map<Player, Int> {
            return players.associateWith { it against dealer }.map { (player, result) ->
                when (result) {
                    ResultType.WIN -> player to player.getProfit().toInt()
                    ResultType.TIE -> player to 0
                    ResultType.LOSE -> player to -player.bettingMoney!!.toInt()
                }
            }.toMap()
        }
    }
}
