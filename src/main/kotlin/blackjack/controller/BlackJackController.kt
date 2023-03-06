package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        with(initBlackJack()) {
            setUpCard(this)
            takeTurns(this)
            takeDealerTurn(this)

            OutputView.printScores(getGameScores())
            OutputView.printResults(getGameResults())
        }
    }

    private fun initBlackJack(): BlackJack =
        BlackJackBuilder {
            cardDeck()
            participants {
                dealer()
                players(InputView.inputNames())
            }
        }

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        // OutputView.printInitialHands(blackJack.getInitialHands())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }

    private fun takeTurns(blackJack: BlackJack) {
        blackJack.getPlayers().forEach { player ->
            takePlayerTurn(blackJack, player)
        }
        OutputView.printInterval()
    }

    private fun takePlayerTurn(blackJack: BlackJack, player: Player) {
        if (!player.canDraw()) {
            val isDraw = InputView.inputDrawCommand(player.name)
            if (!isDraw) return printPlayerHand(player)

            drawCard(blackJack, player)
            takePlayerTurn(blackJack, player)
        }
    }

    private fun drawCard(blackJack: BlackJack, player: Player) {
        blackJack.drawPlayer(player)
        printPlayerHand(player)
    }

    private fun printPlayerHand(player: Player) {
        OutputView.printHand(player.getHand())
    }

    private fun takeDealerTurn(blackJack: BlackJack) {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
        OutputView.printInterval()
    }
}
