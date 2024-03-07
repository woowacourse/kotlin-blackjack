package blackjack.view

import blackjack.model.GameInfo
import blackjack.model.Scoreboard

object OutputView {
    private const val MESSAGE_DISTRIBUTION = "\n%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_RESULT = "%s - 결과: %d"
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
        println("${gameInfo.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalCards(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        println()
        println(
            MESSAGE_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerGameInfo.name,
                    dealerGameInfo.cards.joinToString { "${it.value}${it.shape}" },
                ),
                dealerGameInfo.total,
            ),
        )

        playersGameInfo.forEach { playerStat ->
            println(
                MESSAGE_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        playerStat.name,
                        playerStat.cards.joinToString { "${it.value}${it.shape}" },
                    ),
                    playerStat.total,
                ),
            )
        }
    }

    fun printResult(
        dealerResult: Scoreboard,
        playersResult: List<String>,
        playersGameInfo: List<GameInfo>,
        dealerGameInfo: GameInfo,
    ) {
        println()
        println("## 최종 승패")
        println("${dealerGameInfo.name}: ${getDealerResult(dealerResult)}")
        playersResult.zip(playersGameInfo) { result, stat ->
            println("${stat.name}: $result")
        }
    }

    private fun getDealerResult(dealerResult: Scoreboard): String {
        val winningResult = if (dealerResult.win > 0) "${dealerResult.win}승 " else ""
        val drawResult = if (dealerResult.draw > 0) "${dealerResult.draw}무 " else ""
        val losingResult = if (dealerResult.lose > 0) "${dealerResult.lose}패" else ""
        return winningResult + drawResult + losingResult
    }

    fun printPlayerCards(playersGameInfo: List<GameInfo>) {
        playersGameInfo.forEach { playerStat ->
            printSinglePlayerCards(playerStat)
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
