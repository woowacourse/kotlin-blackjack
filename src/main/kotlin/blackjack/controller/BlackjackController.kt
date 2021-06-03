package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Deck
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players
import blackjack.domain.result.GameResultBoard
import blackjack.view.*

class BlackjackController {
    fun run() {
        val blackjackGame =
            BlackjackGame(Deck(), Dealer(), Players(inputNames().map { Player(it) }))
        blackjackGame.initStage()
        printStatus(blackjackGame.gamersMap())
        while (blackjackGame.runnable()) {
            val currentPlayer = blackjackGame.currentPlayer()
            drawDecision(inputHit(currentPlayer.name), blackjackGame)
            printPlayerStatus(currentPlayer)
        }
        val count = blackjackGame.proceedDealerStageReturnCount()
        repeat(count) { printDealerHit() }

        printResult(blackjackGame.gamers())

        printGameResultBoard(
            GameResultBoard.of(
                blackjackGame.players.players,
                blackjackGame.dealer
            )
        )
    }

    private fun drawDecision(input: String, blackjackGame: BlackjackGame) = when (input) {
        "y" -> {
            blackjackGame.proceedHit()
        }
        "n" -> blackjackGame.next()
        else -> throw IllegalArgumentException("y, n로만 대답할 수 있습니다.")
    }
}
