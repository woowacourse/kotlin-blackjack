package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.ResultType
import blackjack.model.ScoreCalculator
import blackjack.model.card.Card
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT

class OutputView {
    fun printInitialHandOutCardMessage(players: List<Player>) {
        val playerNames = players.joinToString { player -> player.name }
        println(INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT.format(playerNames, INITIAL_HAND_OUT_CARD_COUNT))
    }

    fun printAllPlayerHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(dealer.name, initialDealerHands(dealer.cards)))
        players.forEach { player -> printPlayerHands(player) }
        printContentSeparator()
    }

    fun printPlayerHands(player: Player) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(player.name, getHandsStatus(player.cards)))
    }

    fun printDealerHandStatus(dealerCondition: Boolean) {
        println()
        if (dealerCondition) {
            println(DEALER_HIT_MESSAGE)
        } else {
            println(DEALER_STAY_MESSAGE)
        }
        printContentSeparator()
    }

    fun printFinalHandStatus(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                dealer.name,
                getHandsStatus(dealer.cards),
                ScoreCalculator.calculateOptimalSum(dealer.cards),
            ),
        )
        players.forEach { player -> printFinalPlayerHandStatus(player) }
        printContentSeparator()
    }

    fun printFinalResult(
        playersSummary: Map<Player, ResultType>,
        dealerSummary: Map<ResultType, Int>,
    ) {
        println(FINAL_RESULT_MESSAGE)
        val dealerResult = getDealerResult(dealerSummary)
        println(DEALER_RESULT_FORMAT.format(dealerResult))
        playersSummary.forEach { (player, result) ->
            println(PLAYER_RESULT_FORMAT.format(player.name, result.value))
        }
    }

    private fun initialDealerHands(cards: List<Card>): String {
        return getHandsStatus(listOf(cards.first()))
    }

    private fun getHandsStatus(cards: List<Card>): String {
        return cards.joinToString { card ->
            val cardShape = card.shape
            val cardNumber = card.number
            "${cardNumber.value}${cardShape.type}"
        }
    }

    private fun printFinalPlayerHandStatus(player: Player) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                player.name,
                getHandsStatus(player.cards),
                ScoreCalculator.calculateOptimalSum(player.cards),
            ),
        )
    }

    private fun getDealerResult(summary: Map<ResultType, Int>): String {
        return buildString {
            ResultType.entries.forEach { resultType: ResultType ->
                if (summary[resultType] != null) {
                    append("${summary[resultType]}${resultType.value}").append(" ")
                }
            }
        }.trim()
    }

    private fun printContentSeparator() {
        println()
    }

    companion object {
        private const val INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT = "\n딜러와 %s에게 %d장의 카드를 나누어 주었습니다."
        private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_STAY_MESSAGE = "딜러는 17이상이라 카드를 받지 않았습니다."
        private const val FINAL_RESULT_MESSAGE = "## 최종 승패"
        private const val HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s"
        private const val FINAL_HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s - 결과: %d"
        private const val DEALER_RESULT_FORMAT = "딜러: %s"
        private const val PLAYER_RESULT_FORMAT = "%s: %s"
    }
}
