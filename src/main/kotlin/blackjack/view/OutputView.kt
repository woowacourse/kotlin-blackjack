package blackjack.view

import blackjack.model.ScoreCalculator
import blackjack.model.card.Card
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.dto.ParticipantProfitInfo
import blackjack.model.toUi
import blackjack.model.user.Dealer
import blackjack.model.user.Player

class OutputView : BlackjackOutput {
    override fun printInitialHandOutCardMessage(players: List<Player>) {
        val playerNames = players.joinToString { player -> player.name }
        println(INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT.format(playerNames, INITIAL_HAND_OUT_CARD_COUNT))
    }

    override fun printAllPlayerHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(dealer.name, initialDealerHands(dealer.cards)))
        players.forEach { player -> printPlayerHands(player) }
        printContentSeparator()
    }

    override fun printPlayerHands(player: Player) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(player.name, getHandsStatus(player.cards)))
    }

    override fun printDealerHandStatus(isDraw: Boolean) {
        printContentSeparator()
        if (isDraw) {
            println(DEALER_HIT_MESSAGE)
        } else {
            println(DEALER_STAY_MESSAGE)
        }
        printContentSeparator()
    }

    override fun printFinalHandStatus(
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

    override fun printFinalResult(
        playersProfitInfo: List<ParticipantProfitInfo>,
        dealerProfitInfo: ParticipantProfitInfo,
    ) {
        println(FINAL_PROFIT_MESSAGE)
        println(PARTICIPANT_RESULT_FORMAT.format(dealerProfitInfo.name, dealerProfitInfo.profit.amount))
        playersProfitInfo.forEach { playerProfitInfo ->
            println(PARTICIPANT_RESULT_FORMAT.format(playerProfitInfo.name, playerProfitInfo.profit.amount))
        }
    }

    private fun initialDealerHands(cards: List<Card>): String = getHandsStatus(cards.take(1))

    private fun getHandsStatus(cards: List<Card>): String = cards.joinToString { card -> card.toUi() }

    private fun printFinalPlayerHandStatus(player: Player) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                player.name,
                getHandsStatus(player.cards),
                ScoreCalculator.calculateOptimalSum(player.cards),
            ),
        )
    }

    private fun printContentSeparator() {
        println()
    }

    companion object {
        private const val INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT = "\n딜러와 %s에게 %d장의 카드를 나누어 주었습니다."
        private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_STAY_MESSAGE = "딜러는 17이상이라 카드를 받지 않았습니다."
        private const val FINAL_PROFIT_MESSAGE = "## 최종 수익"
        private const val HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s"
        private const val FINAL_HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s - 결과: %d"
        private const val PARTICIPANT_RESULT_FORMAT = "%s: %d"
    }
}
