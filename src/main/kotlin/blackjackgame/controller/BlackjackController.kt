package blackjackgame.controller

import blackjackgame.model.BlackjackGame
import blackjackgame.model.card.Deck
import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player
import blackjackgame.view.inputAskDrawCard
import blackjackgame.view.inputPlayerNames
import blackjackgame.view.printFinalResult
import blackjackgame.view.printStatus

class BlackjackController {

    fun run() {
        val players = inputPlayerNames().map { Player(it) }

        val blackjackGame = BlackjackGame(players, Dealer(), Deck())
        blackjackGame.start()
        // dealer, players --> 딜러와 p1,p2,p3 (구분)
        // 가이드 1줄 (구분)

        // 가이드 카드내역(구분x)
        printStatus(blackjackGame.getInitStatus())

        //players (구분)
        while (blackjackGame.isExistHitPlayer()) {
            val player = blackjackGame.findTurnPlayer()
            val turnResult = blackjackGame.playTurn(inputAskDrawCard(player.name))
            printStatus(turnResult)
        }
        // dealer (구분)

        // dealer + players(구분x)
        printFinalResult(blackjackGame.extractResult())
    }

}

fun main() {
    val blackjackController = BlackjackController()
    blackjackController.run()
}
