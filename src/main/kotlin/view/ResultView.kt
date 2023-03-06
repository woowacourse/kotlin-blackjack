package view

import entity.Dealer
import entity.DealerGameResult
import entity.GameResultType
import entity.Players
import entity.PlayersGameResult

class ResultView {
    fun printGameStatus(dealer: Dealer, players: Players) {
        println(MESSAGE_DEALER_GAME_STATUS.format(ViewUtils.cardsToString(dealer.cards), dealer.cards.sumOfNumbers()))
        players.value.forEach {
            println(MESSAGE_PLAYERS_GAME_STATUS.format(it.name.value, ViewUtils.cardsToString(it.cards), it.cards.sumOfNumbers()))
        }
    }

    fun printGameResult(dealerGameResult: DealerGameResult, playersGameResult: PlayersGameResult) {
        println(MESSAGE_GAME_RESULT)
        println(MESSAGE_DEALER_GAME_RESULT.format(formatDealerGameResult(dealerGameResult)))
        playersGameResult.value.forEach {
            println(MESSAGE_PLAYERS_GAME_RESULT.format(it.key.name.value, gameResultTypeToString(it.value)))
        }
    }

    private fun formatDealerGameResult(dealerGameResult: DealerGameResult): String {
        return dealerGameResult.value.asSequence().joinToString(" ") {
            MESSAGE_GAME_RESULT_TYPE.format(it.value, gameResultTypeToString(it.key))
        }
    }

    private fun gameResultTypeToString(gameResultType: GameResultType): String {
        return when (gameResultType) {
            GameResultType.WIN -> "승"
            GameResultType.LOSE -> "패"
            GameResultType.DRAW -> "무"
        }
    }

    companion object {
        private const val MESSAGE_DEALER_GAME_STATUS = "딜러 카드: %s - 결과: %d"
        private const val MESSAGE_PLAYERS_GAME_STATUS = "%s카드: %s - 결과: %d"
        private const val MESSAGE_GAME_RESULT = "## 최종 승패"
        private const val MESSAGE_DEALER_GAME_RESULT = "딜러: %s"
        private const val MESSAGE_PLAYERS_GAME_RESULT = "%s: %s"
        private const val MESSAGE_GAME_RESULT_TYPE = "%d%s"
    }
}
