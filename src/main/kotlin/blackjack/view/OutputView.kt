package blackjack.view

import blackjack.model.domain.ParticipantInfo
import blackjack.model.store.Store

object OutputView {
    private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_DEALER_HIT = "%s는 16이하라 한장의 카드를 더 받았습니다."
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 수익"
    private const val MESSAGE_CARD_STATUS2 = "%s: %d"
    private const val NEW_LINE = "\n"

    fun printInitialStats(
        store: Store,
    ) {
        printDistributionMessage(store)
        println(getDealerCardResult(store))
        printPlayerCards(store)
        println()
    }

    fun printDealerHit(participantInfo: ParticipantInfo) {
        println(MESSAGE_DEALER_HIT.format(participantInfo.name))
    }

    fun printFinalCards(
        store: Store,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    store.dealerInfo.name,
                    store.dealerInfo.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                ),
                store.dealerInfo.sumCardValues(),
            ),
        )

        store.playersInfo.forEach { participantInfo ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        participantInfo.name,
                        participantInfo.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                    ),
                    participantInfo.sumCardValues(),
                ),
            )
        }
    }

    fun printResult(store: Store) {
        println(MESSAGE_TITLE_RESULT)
        println(MESSAGE_CARD_STATUS2.format(store.dealerInfo.name, getDealerResult(store)))

        store.playersInfo.forEach {
            println(MESSAGE_CARD_STATUS2.format(it.name, it.batingAmount))
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

    private fun printPlayerCards(store: Store) {
        store.playersInfo.forEach { playerStat ->
            printSinglePlayerCards(playerStat)
        }
    }

    private fun getDealerResult(store: Store): Int {
        return store.playersInfo.sumOf { it.batingAmount.unaryMinus() }
    }

    private fun getDealerCardResult(store: Store): String {
        val (shape, cardRank) = store.dealerInfo.cards.first()
        return MESSAGE_CARD_INFO.format(store.dealerInfo.name, "${cardRank.value}${shape.label}")
    }

    private fun printDistributionMessage(store: Store) {
        val names = store.playersInfo.joinToString { it.name }
        println("\n${MESSAGE_DISTRIBUTION.format(store.dealerInfo.name, names)})")
    }
}
