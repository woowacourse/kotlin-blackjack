package view

import entity.Dealer
import entity.Player
import entity.Players

class ResultView {
    fun printGameStatus(dealer: Dealer, players: Players) {
        println(MESSAGE_DEALER_GAME_STATUS.format(ViewUtils.cardsToString(dealer.cards), dealer.cardsNumberSum()))
        players.value.forEach {
            println(MESSAGE_PLAYERS_GAME_STATUS.format(it.userInformation.name.value, ViewUtils.cardsToString(it.cards), it.cardsNumberSum()))
        }
    }

    fun printUserBettingResult(dealerBattingResult: Double, playersBattingResult: MutableMap<Player, Double>) {
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
