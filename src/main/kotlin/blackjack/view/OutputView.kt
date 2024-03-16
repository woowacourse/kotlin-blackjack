package blackjack.view

import blackjack.model.card.Card
import blackjack.model.participant.Amount
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.participant.Role

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        val playersNameMessage = players.playerGroup.map { it.name }.joinToString(PLAYERS_NAME_SEPARATOR)
        lineBreak()
        println(DIVIDE_CARD_FINISH_MESSAGE.format(playersNameMessage))
        println("$DEALER_NAME: ${dealer.initialCardsMessage()}")

        players.playerGroup.forEach {
            println(it.cardsMessage(it.name.toString()))
        }
        lineBreak()
    }

    fun printDealerAdditionalCardMessage() {
        lineBreak()
        println(DEALER_ADDITIONAL_CARD_MESSAGE)
    }

    fun printPlayerCardsMessage(player: Player) {
        println(player.cardsMessage(player.name.toString()))
    }

    fun printPlayersCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        lineBreak()
        println(dealer.cardsMessage("$DEALER_NAME ") + dealer.cardScoreMessage())
        players.playerGroup.forEach {
            println(it.cardsMessage(it.name.toString()) + it.cardScoreMessage())
        }
        lineBreak()
    }

    fun printProfit(
        dealerProfit: Amount,
        players: Players,
    ) {
        println(FINAL_PROFIT_MESSAGE)
        println(profitMessage(DEALER_NAME, dealerProfit))
        players.playerGroup.forEach {
            println(profitMessage(it.name.toString(), it.profitAmount))
        }
    }

    private fun Dealer.initialCardsMessage(): String {
        return cards()[0].message()
    }

    private fun Role.cardsMessage(name: String): String {
        return "${name}카드: ${cards().joinToString(separator = CARDS_SEPARATOR, transform = { it.message() })}"
    }

    private fun Role.cardScoreMessage() = " - 결과: ${score()}"

    private fun Card.message() = "${denomination.value}${suite.value}"

    private fun profitMessage(
        name: String,
        profitAmount: Amount,
    ) = "$name: ${profitAmount.price}"

    private fun lineBreak() = println()

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val PLAYERS_NAME_SEPARATOR = ", "
        private const val CARDS_SEPARATOR = ", "
        private const val DIVIDE_CARD_FINISH_MESSAGE = "${DEALER_NAME}와 %s에게 2장의 나누었습니다."
        private const val DEALER_ADDITIONAL_CARD_MESSAGE = "${DEALER_NAME}는 16이하라 한장의 카드를 더 받았습니다."
        private const val FINAL_PROFIT_MESSAGE = "## 최종 수익"
    }
}
