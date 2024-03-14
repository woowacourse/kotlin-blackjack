package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.PlayerResult

class GameResult(
    val dealer: Dealer,
    val playerResults: List<PlayerResult>,
) {
    fun getPlayerWinCount(): Int = playerResults.count { it.result == Result.PLAYER_WIN }

    fun getDealerWinCount(): Int = playerResults.count { it.result == Result.DEALER_WIN }

    fun getDrawCount(): Int = playerResults.count { it.result == Result.DRAW }

    class GameResult(val dealer: Dealer, val playerResults: List<PlayerResult>) {
        fun calculateFinalProfits() {
            playerResults.forEach { playerResult ->
                playerResult.player.updateProfit(playerResult.result)
            }
            val dealerProfit =
                playerResults.sumOf { playerResult ->
                    when (playerResult.result) {
                        Result.DEALER_WIN -> playerResult.player.bettingMoney.bettingMoney
                        Result.PLAYER_WIN -> -playerResult.player.bettingMoney.bettingMoney
                        else -> 0 // 무승부
                    }
                }
            // 딜러 객체에 profit 필드가 있다고 가정하고 업데이트
            dealer.profit += dealerProfit
        }
    }
}
