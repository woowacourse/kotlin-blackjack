package blackjack.view

import blackjack.model.gameInfo.GameInfo
import blackjack.model.participants.Judge

object ResultView {
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 금액"
    private const val MESSAGE_INCOME_STATUS = "%s: %s"

    fun printFinalCards(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        println()
        println(dealerGameInfo.toString())

        playersGameInfo.forEach { playerStat ->
            println(playerStat.toString())
        }
    }

    fun printResult(judge: Judge) {
        println(MESSAGE_TITLE_RESULT)
        with(judge) {
            println(MESSAGE_INCOME_STATUS.format(dealer.name, getDealerIncome().amount))
            getPlayersIncome().zip(players) { money, stat ->
                println(MESSAGE_INCOME_STATUS.format(stat.name, money.amount))
            }
        }
    }
}
