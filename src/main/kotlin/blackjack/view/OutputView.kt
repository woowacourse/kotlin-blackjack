package blackjack.view

import blackjack.model.ScoreCalculator
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardNumber.ACE
import blackjack.model.card.CardNumber.EIGHT
import blackjack.model.card.CardNumber.FIVE
import blackjack.model.card.CardNumber.FOUR
import blackjack.model.card.CardNumber.JACK
import blackjack.model.card.CardNumber.KING
import blackjack.model.card.CardNumber.NINE
import blackjack.model.card.CardNumber.QUEEN
import blackjack.model.card.CardNumber.SEVEN
import blackjack.model.card.CardNumber.SIX
import blackjack.model.card.CardNumber.TEN
import blackjack.model.card.CardNumber.THREE
import blackjack.model.card.CardNumber.TWO
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.card.Shape
import blackjack.model.card.Shape.CLOVER
import blackjack.model.card.Shape.DIAMOND
import blackjack.model.card.Shape.HEART
import blackjack.model.card.Shape.SPADE
import blackjack.model.dto.ParticipantProfitInfo
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

    private fun Card.toUi(): String = "${this.number.toUi()}${this.shape.toUi()}"

    private fun CardNumber.toUi(): String =
        when (this) {
            ACE -> "A"
            TWO -> "2"
            THREE -> "3"
            FOUR -> "4"
            FIVE -> "5"
            SIX -> "6"
            SEVEN -> "7"
            EIGHT -> "8"
            NINE -> "9"
            TEN -> "10"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
        }

    private fun Shape.toUi(): String =
        when (this) {
            SPADE -> "스페이드"
            DIAMOND -> "다이아몬드"
            HEART -> "하트"
            CLOVER -> "클로버"
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
