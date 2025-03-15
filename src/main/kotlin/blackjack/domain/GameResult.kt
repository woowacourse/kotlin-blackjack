package blackjack.domain

import blackjack.domain.betting.BettingInfo
import blackjack.domain.betting.ProfitAmount
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.state.ResultState

class GameResult(val winStatus: Map<Player, ResultState>) {
    fun calculateProfits(bettingInfos: List<BettingInfo>): Map<Player, ProfitAmount> {
        return bettingInfos.associate { bettingInfo ->
            val resultState = winStatus[bettingInfo.player] ?: error("[ERROR] 게임 결과가 존재하지 않습니다.")
            val profit =
                when (resultState) {
                    ResultState.WIN -> bettingInfo.bettingAmount.value
                    ResultState.BLACKJACK_WIN -> (bettingInfo.bettingAmount.value * BLACKJACK_PROFIT).toInt()
                    ResultState.LOSE -> -bettingInfo.bettingAmount.value
                    ResultState.DRAW -> 0
                }
            bettingInfo.player to ProfitAmount(profit)
        }
    }

    companion object {
        fun create(
            dealer: Dealer,
            players: List<Player>,
        ): GameResult {
            return GameResult(
                players.associateWith { player ->
                    ResultState.from(player, dealer)
                },
            )
        }

        private const val BLACKJACK_PROFIT = 1.5
    }
}
