package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        println(INIT_CARD_MESSAGE.format(players.gamePlayers.joinToString(SPLIT_DELIMITER) { it.name }))
        println(DEALER_FIRST_CARD_MESSAGE.format(dealer.getFirstCard()))
        players.gamePlayers.forEach { player ->
            println(PLAYER_CARDS_MESSAGE.format(player.name, player.getAllCards()))
        }
    }

    fun printPlayerCard(player: Player) {
        println(PLAYER_CARDS_MESSAGE.format(player.name, player.getAllCards()))
    }

    fun printBustMessage() {
        println(BUST_MESSAGE)
    }

    fun printDealerAddCard() {
        println(DEALER_ADD_CARD_MESSAGE)
    }

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
        result.values.sum().let { playerTotalprofit ->
            println(DEALER_PROFIT_MESSAGE.format(-playerTotalprofit))
        }

        result.forEach { (playerName, profit) ->
            println("$playerName: $profit")
        }
    }

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val INIT_CARD_MESSAGE = "\n딜러와 %s명의 플레이어에게 2장의 카드를 나누었습니다."
        private const val DEALER_FIRST_CARD_MESSAGE = "딜러: %s"
        private const val PLAYER_CARDS_MESSAGE = "%s 카드: %s"
        private const val BUST_MESSAGE = "Bust! 더이상 카드를 받을 수 없습니다."
        private const val DEALER_ADD_CARD_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_FINAL_CARDS_MESSAGE = "\n딜러 카드: %s - 결과: %d"
        private const val PLAYER_FINAL_CARDS_MESSAGE = "%s 카드: %s - 결과: %d"
        private const val FINAL_RESULT_MESSAGE = "\n## 최종 승패"
        private const val DEALER_PROFIT_MESSAGE = "딜러: %d"
    }
}
