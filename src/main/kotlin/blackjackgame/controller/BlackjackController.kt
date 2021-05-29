package blackjackgame.controller

import blackjackgame.model.card.Deck
import blackjackgame.model.player.Player
import blackjackgame.model.player.Players
import blackjackgame.view.inputPlayerNames
import blackjackgame.view.printStatus

class BlackjackController {

    fun run() {
        val players = Players(inputPlayerNames().map { Player(it) })
        val deck = Deck()
        players.drawInitCards(deck)
        printStatus(players.map { Pair(it.name, it.getInitCards()) })

    }
}

fun main() {
    val blackjackController = BlackjackController()
    blackjackController.run()
}
