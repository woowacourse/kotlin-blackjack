package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.Player
import blackjack.domain.RandomCardGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    private lateinit var blackJack: BlackJack

    fun start() {
        initBlackJack()
        setUpCard()
        OutputView.printInterval()

        takeTurns()
        OutputView.printInterval()

        takeDealerTurn()
        OutputView.printInterval()
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
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }

    private fun takeTurns() {
        blackJack.getPlayers().forEach(::takePlayerTurn)
    }

    private fun takePlayerTurn(player: Player) {
        while (!player.isBust()) {
            val command = InputView.inputDrawCommand(player.name)
            if (command == "n") {
                OutputView.printHand(player.getHand())
                return
            }
            blackJack.drawPlayer(player)
            OutputView.printHand(player.getHand())
        }
    }

    private fun takeDealerTurn() {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
    }
}
