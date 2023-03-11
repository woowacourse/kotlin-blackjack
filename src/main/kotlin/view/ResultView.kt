package view

import entity.users.Dealer
import entity.users.Player
import entity.users.Players
import view.ViewUtils.Companion.isString

class ResultView {
    fun printStatus(dealer: Dealer, players: Players) {
        println(MESSAGE_DEALER_GAME_STATUS.format(dealer.cards.isString(), dealer.cardsNumberSum()))
        players.value.forEach {
            println(MESSAGE_PLAYERS_GAME_STATUS.format(it.userInformation.name.value, it.cards.isString(), it.cardsNumberSum()))
        }
    }

    fun printBettingResult(dealerBattingResult: Double, playersBattingResult: MutableMap<Player, Double>) {
        println(MESSAGE_TOTAL_PROFIT_MONEY)
        println(MESSAGE_DEALER_BETTING_RESULT.format(dealerBattingResult.toInt()))
        playersBattingResult.forEach { (player, result) ->
            println(MESSAGE_PLAYERS_BETTING_RESULT.format(player.userInformation.name.value, result.toInt()))
        }
    }

    companion object {
        private const val MESSAGE_DEALER_GAME_STATUS = "딜러 카드: %s - 결과: %d"
        private const val MESSAGE_PLAYERS_GAME_STATUS = "%s카드: %s - 결과: %d"
        private const val MESSAGE_TOTAL_PROFIT_MONEY = "\n##최종 수익"
        private const val MESSAGE_DEALER_BETTING_RESULT = "딜러: %d"
        private const val MESSAGE_PLAYERS_BETTING_RESULT = "%s: %d"
    }
}
