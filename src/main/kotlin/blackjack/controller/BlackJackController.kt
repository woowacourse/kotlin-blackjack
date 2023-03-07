package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        with(initBlackJack()) {
            setUpCard(this)
            takeTurns(this)
            takeDealerTurn(this)

            OutputView.printScores(getCards(), getTotalScores())
            OutputView.printResults(getGameResults())
        }
    }

    private fun initBlackJack(): BlackJack = BlackJack(CardDeck(), Participants(Dealer(), enrollPlayers()))

    private fun enrollPlayers(): Players = Players(InputView.inputNames().map(::Player))

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        OutputView.printFirstOpenCards(blackJack.getFirstOpenCards())
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
            if (!isDraw) return printPlayerCards(player)

            drawCard(blackJack, player)
            takePlayerTurn(blackJack, player)
        }
    }

    private fun drawCard(blackJack: BlackJack, player: Player) {
        blackJack.drawPlayer(player)
        printPlayerCards(player)
    }

    private fun printPlayerCards(player: Player) {
        OutputView.printCards(mapOf(player.name to player.getCards()))
    }

    private fun takeDealerTurn(blackJack: BlackJack) {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
        OutputView.printInterval()
    }
}
