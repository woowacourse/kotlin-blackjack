package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Deck
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Money
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players
import blackjack.domain.result.GameResultBoard
import blackjack.domain.result.ProfitResult
import blackjack.view.*

class BlackjackController {
    fun run() {
        val blackjackGame =
            BlackjackGame(Deck(), Dealer(), Players(inputNames().map { Player(it) }))
        initStage(blackjackGame)
        printStatus(blackjackGame.gamersMap())
        proceedGame(blackjackGame)
        showResult(blackjackGame)
    }

    private fun initStage(blackjackGame: BlackjackGame) {
        blackjackGame.players.players.forEach { it.money = Money(inputMoney(it.name)) }
        blackjackGame.initStage()
    }

    private fun proceedGame(blackjackGame: BlackjackGame) {
        proceedPlayersStage(blackjackGame)
        proceedDealerStage(blackjackGame)
    }

    private fun proceedDealerStage(blackjackGame: BlackjackGame) {
        val count = blackjackGame.proceedDealerStageReturnCount()
        repeat(count) { printDealerHit() }
    }


    private fun proceedPlayersStage(blackjackGame: BlackjackGame) {
        while (blackjackGame.runnable()) {
            val currentPlayer = blackjackGame.currentPlayer()
            drawDecision(inputHit(currentPlayer.name), blackjackGame)
            printPlayerStatus(currentPlayer)
        }
    }

    private fun drawDecision(input: String, blackjackGame: BlackjackGame) = when (input) {
        "y" -> {
            blackjackGame.proceedHit()
        }
        "n" -> blackjackGame.next()
        else -> throw IllegalArgumentException("y, n로만 대답할 수 있습니다.")
    }

    private fun showResult(blackjackGame: BlackjackGame) {
        printResult(blackjackGame.gamers())
        printGameResultBoard(
            GameResultBoard.of(
                blackjackGame.players.players,
                blackjackGame.dealer
            )
        )
        printProfitResult(ProfitResult.of(blackjackGame.players, blackjackGame.dealer))
    }
}
