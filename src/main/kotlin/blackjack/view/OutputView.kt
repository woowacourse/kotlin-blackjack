package blackjack.view

import blackjack.model.Scoreboard
import blackjack.model.Stat

class OutputView {
    fun printInitialStats(
        dealerStat: Stat,
        playerStats: List<Stat>,
    ) {
        printDistributionMessage(playerStats, dealerStat)
        println(getDealerCardResult(dealerStat))
        printPlayerCards(playerStats)
    }

    fun printDealerHit(dealerStat: Stat) {
        println("${dealerStat.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalCards(
        dealerStat: Stat,
        playerStats: List<Stat>,
    ) {
        println(
            MESSAGE_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerStat.name,
                    dealerStat.cards.joinToString { "${it.value}${it.title}" },
                ),
            ),
        )

        playerStats.forEach { playerStat ->
            MESSAGE_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    playerStat.name,
                    playerStat.cards.joinToString { "${it.value}${it.title}" },
                ),
            )
        }
    }

    fun printResult(
        dealerResult: Scoreboard,
        playerResult: List<String>,
        playerStats: List<Stat>,
        dealerStat: Stat,
    ) {
        println("## 최종 승패")
        println("${dealerStat.name}: ${getDealerResult(dealerResult)}")
        playerResult.zip(playerStats) { result, stat ->
            println("${stat.name}: $result")
        }
    }

    private fun getDealerResult(dealerResult: Scoreboard): String {
        val winningResult = if (dealerResult.win > 0) "${dealerResult.win}승 " else ""
        val drawResult = if (dealerResult.draw > 0) "${dealerResult.draw}무 " else ""
        val losingResult = if (dealerResult.lose > 0) "${dealerResult.lose}패" else ""
        return winningResult + drawResult + losingResult
    }

    private fun printPlayerCards(playerStats: List<Stat>) {
        playerStats.forEach { playerStat ->
            println(
                MESSAGE_CARD_INFO.format(
                    playerStat.name,
                    playerStat.cards.joinToString { "${it.value}${it.title}" },
                ),
            )
        }
    }

    private fun getDealerCardResult(dealerStat: Stat): String {
        return MESSAGE_CARD_INFO.format(
            dealerStat.name,
            dealerStat.cards.joinToString { "${it.value}${it.title}" },
        )
    }

    private fun printDistributionMessage(
        playerStats: List<Stat>,
        dealerStat: Stat,
    ) {
        val names = playerStats.joinToString { it.name }
        println(MESSAGE_DISTRIBUTION.format(dealerStat.name, names))
    }

    companion object {
        private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
        private const val MESSAGE_CARD_INFO = "%s카드: %s"
        private const val MESSAGE_RESULT = "%s - 결과: %d"
    }
}
