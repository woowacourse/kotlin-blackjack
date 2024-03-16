package blackjack.view

import blackjack.model.card.Card
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
        println("딜러: ${dealer.initialCardsMessage()}")

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
        println(dealer.cardsMessage(DEALER_NAME_MESSAGE) + dealer.cardScoreMessage())
        players.playerGroup.forEach {
            println(it.cardsMessage(it.name.toString()) + it.cardScoreMessage())
        }
        lineBreak()
    }

    private fun Dealer.initialCardsMessage(): String {
        return cards()[0].message()
    }

    private fun Role.cardsMessage(name: String): String {
        return "${name}카드: ${cards().joinToString(separator = CARDS_SEPARATOR, transform = { it.message() })}"
    }

    private fun Role.cardScoreMessage() = " - 결과: ${score()}"

    private fun Card.message() = "${denomination.value}${suite.value}"

    private fun lineBreak() = println()

    companion object {
        private const val PLAYERS_NAME_SEPARATOR = ", "
        private const val CARDS_SEPARATOR = ", "
        private const val DIVIDE_CARD_FINISH_MESSAGE = "딜러와 %s에게 2장의 나누었습니다."
        private const val DEALER_ADDITIONAL_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NAME_MESSAGE = "딜러 "
    }
}
