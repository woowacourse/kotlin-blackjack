package blackjack.view

import blackjack.model.domain.Judge
import blackjack.model.domain.ParticipantInfo
import blackjack.model.entitiy.GameResult
import blackjack.model.store.Store

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

    fun printResult(store: Store, judge: Judge) {
        println(MESSAGE_TITLE_RESULT)
        println(MESSAGE_CARD_STATUS.format(store.dealerInfo.name, getDealerResult(judge.getGameResults())))

        judge.getGameResults().zip(store.playersInfo) { result, stat ->
            println(MESSAGE_CARD_STATUS.format(stat.name, result.value))
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

    private fun getDealerResult(result: List<GameResult>): String {
        val winCount = result.count { it == GameResult.WIN }
        val drawCount = result.count { it == GameResult.DRAW }
        val loseCount = result.count { it == GameResult.LOSE }

        val winningResult = if (loseCount > 0) "${loseCount}승" else EMPTY_STRING
        val drawResult = if (drawCount > 0) "${drawCount}무 " else EMPTY_STRING
        val losingResult = if (winCount > 0) "${winCount}패 " else EMPTY_STRING

        return losingResult + drawResult + winningResult
    }

    private fun getDealerCardResult(store: Store): String {
        val (shape, cardRank) = store.dealerInfo.cards.first()
        return MESSAGE_CARD_INFO.format(store.dealerInfo.name, "${cardRank.value}${shape.label}")
    }

    private fun printDistributionMessage(store: Store) {
        val names = store.playersInfo.joinToString { it.name }
        println(MESSAGE_DISTRIBUTION.format(store.dealerInfo.name, names))
    }
}
