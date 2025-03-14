package blackjack.view

import blackjack.model.GameManager.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.amount.WinningMoney
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardNumber.ACE
import blackjack.model.card.CardNumber.JACK
import blackjack.model.card.CardNumber.KING
import blackjack.model.card.CardNumber.QUEEN
import blackjack.model.card.Shape
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player
import java.text.DecimalFormat

class OutputView {
    fun printInitialHandOutCardMessage(players: List<Player>) {
        val playerNames = players.joinToString { player -> player.name }
        println(INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT.format(playerNames, INITIAL_HAND_OUT_CARD_COUNT))
    }

    fun printAllPlayerHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(dealer.name, formattingCardStatus(dealer.getInitialCard())))
        players.forEach { player -> printPlayerHands(player) }
        println()
    }

    fun printPlayerHands(player: Player) {
        println(HANDS_STATUS_MESSAGE_FORMAT.format(player.name, formattingCardStatus(player.getInitialCard())))
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
                formattingCardStatus(dealer.cards),
                convertScore(dealer),
            ),
        )
        players.forEach { player -> printFinalPlayerHandStatus(player) }
        println()
    }

    fun printFinalResult(profitResult: Map<Player, WinningMoney>) {
        val dealerProfit = profitResult.values.sumOf { profit -> profit.winningMoney } * DEALER_PROFIT_MULTIPLIER
        println(FINAL_RESULT_MESSAGE)
        println(DEALER_RESULT_FORMAT.format(dealerProfit.formatAmount()))
        profitResult.forEach { (player, profit) ->
            println(PLAYER_RESULT_FORMAT.format(player.name, profit.winningMoney.formatAmount()))
        }
    }

    private fun formattingCardStatus(cards: List<Card>): String {
        return cards.joinToString { card ->
            "${getNumberDisplayName(card.number)}${getShapeDisplayName(card.shape)}"
        }
    }

    private fun printFinalPlayerHandStatus(player: Player) {
        println(
            FINAL_HANDS_STATUS_MESSAGE_FORMAT.format(
                player.name,
                formattingCardStatus(player.cards),
                convertScore(player),
            ),
        )
    }

    private fun convertScore(participant: Participant): String {
        return if (participant.isBlackjack()) DISPLAY_NAME_BLACKJACK else participant.score.toString()
    }

    private fun getNumberDisplayName(number: CardNumber): String {
        return when (number) {
            ACE -> DISPLAY_NAME_ACE
            JACK -> DISPLAY_NAME_JACK
            QUEEN -> DISPLAY_NAME_QUEEN
            KING -> DISPLAY_NAME_KING
            else -> (number.ordinal + 1).toString()
        }
    }

    private fun getShapeDisplayName(shape: Shape): String {
        return when (shape) {
            Shape.SPADE -> DISPLAY_NAME_SPADE
            Shape.DIAMOND -> DISPLAY_NAME_DIAMOND
            Shape.HEART -> DISPLAY_NAME_HEART
            Shape.CLOVER -> DISPLAY_NAME_CLOVER
        }
    }

    private fun Double.formatAmount(): String {
        val formatter = DecimalFormat("#.##")
        return formatter.format(this)
    }

    companion object {
        private const val INITIAL_HAND_OUT_CARD_MESSAGE_FORMAT = "\n딜러와 %s에게 %d장의 카드를 나누어 주었습니다."
        private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_STAY_MESSAGE = "딜러는 17이상이라 카드를 받지 않았습니다."
        private const val FINAL_RESULT_MESSAGE = "## 최종 수익"
        private const val HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s"
        private const val FINAL_HANDS_STATUS_MESSAGE_FORMAT = "%s 카드: %s - 결과: %s"
        private const val DEALER_RESULT_FORMAT = "딜러: %s"
        private const val PLAYER_RESULT_FORMAT = "%s: %s"

        private const val DISPLAY_NAME_BLACKJACK = "블랙잭"
        private const val DISPLAY_NAME_ACE = "A"
        private const val DISPLAY_NAME_JACK = "J"
        private const val DISPLAY_NAME_QUEEN = "Q"
        private const val DISPLAY_NAME_KING = "K"
        private const val DISPLAY_NAME_SPADE = " ♠️"
        private const val DISPLAY_NAME_DIAMOND = " ♦️"
        private const val DISPLAY_NAME_HEART = " ♥️"
        private const val DISPLAY_NAME_CLOVER = " ♣️"
        private const val DEALER_PROFIT_MULTIPLIER = -1.0
    }
}
