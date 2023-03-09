package view

import entity.Dealer
import entity.DealerGameResult
import entity.Player
import entity.Players
import entity.PlayersGameResult
import view.ViewUtils.Companion.isString

class ResultView {
    fun printGameStatus(dealer: Dealer, players: Players) {
        println(MESSAGE_DEALER_GAME_STATUS.format(ViewUtils.cardsToString(dealer.cards), dealer.cardsNumberSum()))
        players.value.forEach {
            println(MESSAGE_PLAYERS_GAME_STATUS.format(it.userInformation.name.value, ViewUtils.cardsToString(it.cards), it.cardsNumberSum()))
        }
    }

    fun printGameResult(dealerGameResult: DealerGameResult, playersGameResult: PlayersGameResult) {
        println(MESSAGE_GAME_RESULT)
        println(MESSAGE_DEALER_GAME_RESULT.format(formatDealerGameResult(dealerGameResult)))
        playersGameResult.value.forEach {
            println(MESSAGE_PLAYERS_GAME_RESULT.format(it.key.userInformation.name.value, it.value.isString()))
        }
    }

    private fun formatDealerGameResult(dealerGameResult: DealerGameResult): String {
        return dealerGameResult.value.asSequence().joinToString(" ") {
            MESSAGE_GAME_RESULT_TYPE.format(it.value, it.key.isString())
        }
    }

    fun printUserBettingResult(dealerBattingResult: Double, playersBattingResult: MutableMap<Player, Double>) {
        println(MESSAGE_TOTAL_PROFIT_MONEY)
        println(MESSAGE_DEALER_BETTING_RESULT)
        playersBattingResult.forEach { (name, result) ->
            println(MESSAGE_PLAYERS_BETTING_RESULT.format(name, result))
        }
    }

    companion object {
        private const val MESSAGE_DEALER_GAME_STATUS = "딜러 카드: %s - 결과: %d"
        private const val MESSAGE_PLAYERS_GAME_STATUS = "%s카드: %s - 결과: %d"
        private const val MESSAGE_GAME_RESULT = "## 최종 승패"
        private const val MESSAGE_DEALER_GAME_RESULT = "딜러: %s"
        private const val MESSAGE_PLAYERS_GAME_RESULT = "%s: %s"
        private const val MESSAGE_GAME_RESULT_TYPE = "%d%s"
        private const val MESSAGE_TOTAL_PROFIT_MONEY = "##최종 수익"
        private const val MESSAGE_DEALER_BETTING_RESULT = "딜러: %d"
        private const val MESSAGE_PLAYERS_BETTING_RESULT = "%s: %d"
    }
}
