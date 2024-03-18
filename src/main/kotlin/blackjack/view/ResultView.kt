package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Players

class ResultView {
    fun printCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        println(DEALER_FINAL_CARDS_MESSAGE.format(dealer.getAllCards(), dealer.calculateScore()))
        players.gamePlayers.forEach { player ->
            println(PLAYER_FINAL_CARDS_MESSAGE.format(player.name, player.getAllCards(), player.calculateScore()))
        }
    }

    fun printGameResult(result: Map<String, Int>) {
        println(FINAL_RESULT_MESSAGE)
        result.values.sum().let { playerTotalProfit ->
            println(DEALER_PROFIT_MESSAGE.format(-playerTotalProfit))
        }

        result.forEach { (playerName, profit) ->
            println("$playerName: ${profit}원")
        }
    }

    companion object {
        private const val DEALER_FINAL_CARDS_MESSAGE = "\n딜러 카드: %s - 결과: %d"
        private const val PLAYER_FINAL_CARDS_MESSAGE = "%s 카드: %s - 결과: %d"
        private const val FINAL_RESULT_MESSAGE = "\n## 최종 승패"
        private const val DEALER_PROFIT_MESSAGE = "딜러: %d원"
    }
}
