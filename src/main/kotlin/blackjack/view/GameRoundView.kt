package blackjack.view

import blackjack.model.GameInfo

object GameRoundView {
    private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_DEALER_HIT = "%s는 16이하라 한장의 카드를 더 받았습니다."
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

    fun printSinglePlayerCards(gameInfo: GameInfo) {
        println(
            MESSAGE_CARD_INFO.format(
                gameInfo.name,
                gameInfo.cards.joinToString { "${it.title}${it.shape.title}" },
            ),
        )
    }

    fun printNewLine() = print(NEW_LINE)

    private fun printPlayerCards(playersGameInfo: List<GameInfo>) {
        playersGameInfo.forEach { playerStat ->
            printSinglePlayerCards(playerStat)
        }
    }

    private fun getDealerCardResult(gameInfo: GameInfo): String {
        return MESSAGE_CARD_INFO.format(
            gameInfo.name,
            gameInfo.cards.joinToString { "${it.value}${it.shape.title}" },
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
