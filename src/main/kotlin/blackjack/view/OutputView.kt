package blackjack.view

import blackjack.model.domain.ParticipantInfo

object OutputView {
    private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_DEALER_HIT = "%s는 16이하라 한장의 카드를 더 받았습니다."
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 수익"
    private const val MESSAGE_CARD_STATUS = "%s: %d"
    private const val NEW_LINE = "\n"

    fun printInitialStats(
        playersInfo: List<ParticipantInfo>,
        dealerInfo: ParticipantInfo,
    ) {
        printDistributionMessage(playersInfo, dealerInfo)
        println(getDealerCardResult(dealerInfo))
        printPlayerCards(playersInfo)
        println()
    }

    fun printDealerHit(participantInfo: ParticipantInfo) {
        println(MESSAGE_DEALER_HIT.format(participantInfo.name))
    }

    fun printFinalCards(
        playersInfo: List<ParticipantInfo>,
        dealerInfo: ParticipantInfo,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerInfo.name,
                    dealerInfo.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                ),
                dealerInfo.sumCardValues(),
            ),
        )

        playersInfo.forEach { playerInfo ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        playerInfo.name,
                        playerInfo.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                    ),
                    playerInfo.sumCardValues(),
                ),
            )
        }
    }

    fun printResult(playersInfo: List<ParticipantInfo>, dealerInfo: ParticipantInfo) {
        println(MESSAGE_TITLE_RESULT)
        println(MESSAGE_CARD_STATUS.format(dealerInfo.name, getDealerResult(playersInfo)))

        playersInfo.forEach {
            println(MESSAGE_CARD_STATUS.format(it.name, it.batingAmount))
        }
    }

    fun printSinglePlayerCards(participantInfo: ParticipantInfo) {
        println(
            MESSAGE_CARD_INFO.format(
                participantInfo.name,
                participantInfo.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
            ),
        )
    }

    fun printNewLine() = print(NEW_LINE)

    private fun printDistributionMessage(playersInfo: List<ParticipantInfo>, dealerInfo: ParticipantInfo) {
        val names = playersInfo.joinToString { it.name }
        println("\n${MESSAGE_DISTRIBUTION.format(dealerInfo.name, names)})")
    }

    private fun getDealerResult(playersInfo: List<ParticipantInfo>): Int = playersInfo.sumOf { it.batingAmount.unaryMinus() }

    private fun printPlayerCards(playersInfo: List<ParticipantInfo>) {
        playersInfo.forEach { playerInfo ->
            printSinglePlayerCards(playerInfo)
        }
    }

    private fun getDealerCardResult(dealerInfo: ParticipantInfo): String {
        val (shape, cardRank) = dealerInfo.cards.first()
        return MESSAGE_CARD_INFO.format(dealerInfo.name, "${cardRank.value}${shape.label}")
    }
}
