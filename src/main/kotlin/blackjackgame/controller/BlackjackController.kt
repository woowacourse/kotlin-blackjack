package blackjackgame.controller

import blackjackgame.model.BlackjackGame
import blackjackgame.model.card.Deck
import blackjackgame.model.player.Player
import blackjackgame.model.player.Players
import blackjackgame.view.inputAskDrawCard
import blackjackgame.view.inputPlayerNames
import blackjackgame.view.printStatus

class BlackjackController {

    fun run() {
        val players = Players(inputPlayerNames().map { Player(it) })
        val blackjackGame = BlackjackGame(players, Deck())
        blackjackGame.start()
        printStatus(blackjackGame.getInitStatus())

        while (blackjackGame.isExistHitPlayer()) {
            val player = blackjackGame.findTurnPlayer()
            val turnResult = blackjackGame.playTurn(inputAskDrawCard(player.name))
            printStatus(turnResult)
        }
    }
}

fun main() {
    val blackjackController = BlackjackController()
    blackjackController.run()
}
