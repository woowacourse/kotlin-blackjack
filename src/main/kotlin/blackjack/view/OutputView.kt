package blackjack.view

import blackjack.model.card.Card
import blackjack.model.Dealer
import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.card.CardNumber
import blackjack.model.card.CardNumber.ACE
import blackjack.model.card.CardNumber.JACK
import blackjack.model.card.CardNumber.KING
import blackjack.model.card.CardNumber.QUEEN
import blackjack.model.Player
import blackjack.model.ResultCalculator.adjustScore
import blackjack.model.ResultCalculator.calculateTotalScore
import blackjack.model.ResultType
import blackjack.model.card.Shape

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
        println()
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
        println()
    }

    fun printFinalHandStatus(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                dealer.name,
                getHandsStatus(dealer.cards),
                calculateTotalScore(dealer.cards),
            ),
        )
        players.forEach { player -> printFinalPlayerHandStatus(player) }
        println()
    }

    fun printFinalResult(
        resultMap: Map<Player, ResultType>,
        dealerResult: Map<ResultType, Int>,
    ) {
        println(FINAL_RESULT_MESSAGE)
        val dealerSummary = dealerResult.map { "${it.value}${getResultDisplayName(it.key)}" }
        println(DEALER_RESULT_FORMAT.format(dealerSummary.joinToString(" ")))
        resultMap.forEach { (player, result) ->
            println(PLAYER_RESULT_FORMAT.format(player.name, getResultDisplayName(result)))
        }
    }

    private fun initialDealerHands(cards: List<Card>): String {
        return getHandsStatus(listOf(cards.first()))
    }

    private fun getHandsStatus(cards: List<Card>): String {
        return cards.joinToString { card ->
            "${getNumberDisplayName(card.number)}${getShapeDisplayName(card.shape)}"
        }
    }

    private fun printFinalPlayerHandStatus(player: Player) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                player.name,
                getHandsStatus(player.cards),
                adjustScore(player.cards),
            ),
        )
    }

    private fun getNumberDisplayName(number: CardNumber): String {
        return when (number) {
            ACE -> "A"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            else -> (number.ordinal + 1).toString()
        }
    }

    private fun getShapeDisplayName(shape: Shape): String {
        return when (shape) {
            Shape.SPADE -> "스페이드"
            Shape.DIAMOND -> "다이아몬드"
            Shape.HEART -> "하트"
            Shape.CLOVER -> "클로버"
        }
    }

    private fun getResultDisplayName(result: ResultType): String {
        return when (result) {
            ResultType.WIN -> "승"
            ResultType.TIE -> "무"
            ResultType.LOSS -> "패"
        }
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
