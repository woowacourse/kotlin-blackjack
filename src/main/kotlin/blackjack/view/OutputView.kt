package blackjack.view

import blackjack.model.Stat

class OutputView {
    fun printInitialStats(
        dealerStat: Stat,
        playerStats: List<Stat>,
    ) {
        printDistributionMessage(playerStats, dealerStat)
        printDealerCards(dealerStat)
        printPlayerCards(playerStats)
    }

    private fun printPlayerCards(playerStats: List<Stat>) {
        playerStats.forEach { playerStat ->
            println(
                MESSAGE_CARD_INFO.format(
                    playerStat.name,
                    playerStat.cards.joinToString(SEPARATOR_CARDS) { "${it.value}${it.title}" },
                ),
            )
        }
    }

    private fun printDealerCards(dealerStat: Stat) {
        println(
            MESSAGE_CARD_INFO.format(
                dealerStat.name,
                dealerStat.cards.joinToString(SEPARATOR_CARDS) { "${it.value}${it.title}" },
            ),
        )
    }

    private fun printDistributionMessage(
        playerStats: List<Stat>,
        dealerStat: Stat,
    ) {
        val names = playerStats.joinToString(SEPARATOR_CARDS) { it.name }
        println(MESSAGE_DISTRIBUTION.format(dealerStat.name, names))
    }

    companion object {
        private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
        private const val MESSAGE_CARD_INFO = "%s카드: %s"
        private const val SEPARATOR_CARDS = ", "
    }
}
