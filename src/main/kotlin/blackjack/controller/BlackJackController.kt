package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    private lateinit var blackJack: BlackJack

    fun start() {
        initBlackJack()
        setUpCard()
        takeTurns()
        takeDealerTurn()

        OutputView.printScores(blackJack.getGameScores())
        OutputView.printResults(blackJack.getGameResults())
    }

    private fun initBlackJack() {
        blackJack = BlackJackBuilder {
            cardDeck()
            participants {
                dealer()
                players(InputView.inputNames())
            }
        }
    }

    private fun setUpCard() {
        drawInitialCards(blackJack)
        OutputView.printInitialHands(blackJack.getInitialHands())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }

    private fun takeTurns() {
        blackJack.getPlayers().forEach(::takePlayerTurn)
        OutputView.printInterval()
    }

    private fun takePlayerTurn(player: Player) {
        if (!player.isBust()) {
            val isDraw = InputView.inputDrawCommand(player.name)
            if (!isDraw) return printPlayerHand(player)

            drawCard(player)
            takePlayerTurn(player)
        }
    }

    private fun drawCard(player: Player) {
        blackJack.drawPlayer(player)
        printPlayerHand(player)
    }

    private fun printPlayerHand(player: Player) {
        OutputView.printHand(player.getHand())
    }

    private fun takeDealerTurn() {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
        OutputView.printInterval()
    }
}
