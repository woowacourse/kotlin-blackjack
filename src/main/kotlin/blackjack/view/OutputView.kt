package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Role

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        val playersNameMessage = players.playerGroup.map { it.name }.joinToString(", ")
        println("딜러와 ${playersNameMessage}에게 2장의 나누었습니다.")
        println("딜러: ${dealer.getDealerInitCardsMessage()}")

        players.playerGroup.forEach {
            println(it.getPlayerCardsMessage())
        }
    }

    fun Player.getPlayerCardsMessage() = "${name}카드: ${getCardsMessage()}"

    private fun Role.getDealerInitCardsMessage(): String {
        return getCards()[0].toCardMessage()
    }

    private fun Role.getCardsMessage(): String {
        return getCards().joinToString(separator = ", ", transform = { it.toCardMessage() })
    }

    private fun Card.toCardMessage() = "${this.denomination}${this.suite}"
}
