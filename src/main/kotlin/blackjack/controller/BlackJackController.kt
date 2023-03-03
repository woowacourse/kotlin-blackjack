package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.Command
import blackjack.domain.Player
import blackjack.domain.RandomCardGenerator
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
            cardDeck(RandomCardGenerator())
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
        while (!player.isBust()) {
            val command = Command(InputView.inputDrawCommand(player.name))
            val isStop = handleCommand(command, player)
            if (isStop) return
        }
    }

    private fun handleCommand(command: Command, player: Player): Boolean {
        if (command.value == "n") {
            OutputView.printHand(player.getHand())
            return IS_STOP
        }
        blackJack.drawPlayer(player)
        OutputView.printHand(player.getHand())
        return IS_CONTINUE
    }

    private fun takeDealerTurn() {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
        OutputView.printInterval()
    }

    companion object {
        private const val IS_STOP = true
        private const val IS_CONTINUE = false
    }
}
