package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.GameResultType
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Role

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        val playersNameMessage = players.playerGroup.map { it.name }.joinToString(PLAYERS_NAME_SEPARATOR)
        lineBreak()
        println(DIVIDE_CARD_FINISH_MESSAGE.format(playersNameMessage))
        println("딜러: ${dealer.getDealerInitCardsMessage()}")

        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()))
        }
        lineBreak()
    }

    fun printDealerAdditionalCardMessage() {
        lineBreak()
        println(DEALER_ADDITIONAL_CARD_MESSAGE)
    }

    fun printPlayerCardsMessage(player: Player) {
        println(player.getCardsMessage(player.name.toString()))
    }

    fun printPlayersCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        lineBreak()
        println(dealer.getCardsMessage(DEALER_NAME_MESSAGE) + dealer.getPlayerCardResult())
        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()) + it.getPlayerCardResult())
        }
        lineBreak()
    }

    fun printFinalGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        println(FINAL_GAME_RESULT_MESSAGE)
        print("딜러: ")
        printDealerFinalGameResult(dealer)
        printPlayersFinalGameResult(players)
        lineBreak()
    }

    private fun printDealerFinalGameResult(dealer: Dealer) {
        GameResultType.entries.forEach { gameResult ->
            val count = dealer.result.results[gameResult] ?: return@forEach
            print("${count}${gameResult.message} ")
        }
        lineBreak()
    }

    private fun printPlayersFinalGameResult(players: Players) {
        players.playerGroup.forEach {
            println("${it.name}: ${players.playersResult.results[it.name]?.message}")
        }
    }

    private fun Role.getDealerInitCardsMessage(): String {
        return getCards()[0].toCardMessage()
    }

    private fun Role.getCardsMessage(name: String): String {
        return "${name}카드: ${getCards().joinToString(separator = CARDS_SEPARATOR, transform = { it.toCardMessage() })}"
    }

    private fun Card.toCardMessage() = "${denomination.value}${suite.value}"

    private fun Role.getPlayerCardResult() = " - 결과: ${getCardSum()}"

    private fun lineBreak() = println()

    companion object {
        private const val PLAYERS_NAME_SEPARATOR = ", "
        private const val CARDS_SEPARATOR = ", "
        private const val DIVIDE_CARD_FINISH_MESSAGE = "딜러와 %s에게 2장의 나누었습니다."
        private const val DEALER_ADDITIONAL_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val FINAL_GAME_RESULT_MESSAGE = "## 최종 승패"
        private const val DEALER_NAME_MESSAGE = "딜러 "
    }
}
