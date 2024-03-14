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

    fun calculateFinalProfits() {
        val dealerBlackjack = dealer.hand.isBlackjack()
        playerResults.forEach { playerResult ->
            val player = playerResult.player
            val playerBlackjack = player.hand.isBlackjack()
            if (playerBlackjack && !dealerBlackjack) {
                val winnings = (player.bettingMoney.bettingMoney * 1.5).toInt()
                player.profit += winnings
            } else if (playerBlackjack && dealerBlackjack) {
            } else {
                when (playerResult.result) {
                    Result.PLAYER_WIN -> player.profit += player.bettingMoney.bettingMoney
                    Result.DEALER_WIN -> player.profit -= player.bettingMoney.bettingMoney
                    Result.DRAW -> {} // 무승부 (profit 변화 없음)
                }
            }
        }
        dealer.profit = playerResults.sumOf { -it.player.profit }
    }
}
