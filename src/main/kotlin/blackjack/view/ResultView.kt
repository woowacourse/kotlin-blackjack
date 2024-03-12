package blackjack.view

import blackjack.model.GameInfo
import blackjack.model.Judge
import blackjack.model.Scoreboard

object ResultView {
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 승패"
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_CARD_STATUS = "%s: %s"
    private const val EMPTY_STRING = ""

    fun printFinalCards(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerGameInfo.name,
                    dealerGameInfo.cards.joinToString { "${it.title}${it.shape.title}" },
                ),
                dealerGameInfo.sumOfCards,
            ),
        )

        playersGameInfo.forEach { playerStat ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        playerStat.name,
                        playerStat.cards.joinToString { "${it.title}${it.shape.title}" },
                    ),
                    playerStat.sumOfCards,
                ),
            )
        }
    }

    fun printResult(judge: Judge) {
        println(MESSAGE_TITLE_RESULT)
        with(judge) {
            println(MESSAGE_CARD_STATUS.format(dealer.name, getDealerResult(getDealerResult())))
            getPlayerResults().zip(players) { result, stat ->
                println(MESSAGE_CARD_STATUS.format(stat.name, result))
            }
        }
    }

    private fun getDealerResult(dealerResult: Scoreboard): String {
        val winningResult = if (dealerResult.win > 0) "${dealerResult.win}승 " else EMPTY_STRING
        val drawResult = if (dealerResult.draw > 0) "${dealerResult.draw}무 " else EMPTY_STRING
        val losingResult = if (dealerResult.lose > 0) "${dealerResult.lose}패" else EMPTY_STRING
        return winningResult + drawResult + losingResult
    }
}
