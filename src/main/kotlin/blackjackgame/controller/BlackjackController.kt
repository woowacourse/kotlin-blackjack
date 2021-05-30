package blackjackgame.controller

import blackjackgame.model.BlackjackGame
import blackjackgame.model.card.Deck
import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player
import blackjackgame.view.*

class BlackjackController {

    fun run() {
        val players = inputPlayerNames().map { Player(it) }
        val blackjackGame = BlackjackGame(players, Dealer(), Deck())
        blackjackGame.start()
        printStatus(blackjackGame.getInitStatus())

        while (blackjackGame.isExistHitPlayer()) {
            val player = blackjackGame.findTurnPlayer()
            val turnResult = blackjackGame.playTurn(inputAskDrawCard(player.name))
            printStatus(turnResult)
        }

        if (blackjackGame.playDealerTurn()) {
            printDealerTurnResult()
        }

        printFinalResult(blackjackGame.extractResult())
        printWinLoseResult(blackjackGame.extractWinLoseResult())
    }

}

fun main() {
    val blackjackController = BlackjackController()
    blackjackController.run()
}
