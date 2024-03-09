package blackjack.view

import blackjack.model.GameInfo
import blackjack.model.Judge
import blackjack.model.Scoreboard

object OutputView {
    private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_DEALER_HIT = "%s는 16이하라 한장의 카드를 더 받았습니다."
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 승패"
    private const val MESSAGE_CARD_STATUS = "%s: %s"
    private const val EMPTY_STRING = ""
    private const val NEW_LINE = "\n"

    fun printInitialStats(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        printDistributionMessage(playersGameInfo, dealerGameInfo)
        println(getDealerCardResult(dealerGameInfo))
        printPlayerCards(playersGameInfo)
        println()
    }

    fun printDealerHit(gameInfo: GameInfo) {
        println(MESSAGE_DEALER_HIT.format(gameInfo.name))
    }

    fun printFinalCards(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerGameInfo.name,
                    dealerGameInfo.cards.joinToString { "${it.value}${it.shape}" },
                ),
                dealerGameInfo.sumCardValues(),
            ),
        )

        playersGameInfo.forEach { playerStat ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        playerStat.name,
                        playerStat.cards.joinToString { "${it.value}${it.shape}" },
                    ),
                    playerStat.sumCardValues(),
                ),
            )
        }
    }

    fun printResult(judge: Judge) {
        println(MESSAGE_TITLE_RESULT)
        with(judge) {
            println(MESSAGE_CARD_STATUS.format(dealerInfo.name, getDealerResult(getDealerResult())))
            getPlayerResults().zip(playersInfo) { result, stat ->
                println(MESSAGE_CARD_STATUS.format(stat.name, result))
            }
        }
    }

    fun printSinglePlayerCards(gameInfo: GameInfo) {
        println(
            MESSAGE_CARD_INFO.format(
                gameInfo.name,
                gameInfo.cards.joinToString { "${it.value}${it.shape}" },
            ),
        )
    }

    fun printNewLine() = print(NEW_LINE)

    private fun printPlayerCards(playersGameInfo: List<GameInfo>) {
        playersGameInfo.forEach { playerStat ->
            printSinglePlayerCards(playerStat)
        }
    }

    private fun getDealerResult(dealerResult: Scoreboard): String {
        val winningResult = if (dealerResult.win > 0) "${dealerResult.win}승 " else EMPTY_STRING
        val drawResult = if (dealerResult.draw > 0) "${dealerResult.draw}무 " else EMPTY_STRING
        val losingResult = if (dealerResult.lose > 0) "${dealerResult.lose}패" else EMPTY_STRING
        return winningResult + drawResult + losingResult
    }

    private fun getDealerCardResult(gameInfo: GameInfo): String {
        return MESSAGE_CARD_INFO.format(
            gameInfo.name,
            gameInfo.cards.joinToString { "${it.value}${it.shape}" },
        )
    }

    private fun printDistributionMessage(
        playersGameInfo: List<GameInfo>,
        dealerGameInfo: GameInfo,
    ) {
        val names = playersGameInfo.joinToString { it.name }
        println(MESSAGE_DISTRIBUTION.format(dealerGameInfo.name, names))
    }
}
