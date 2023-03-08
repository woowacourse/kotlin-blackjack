package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.Player
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

    private fun initBlackJack(): BlackJack = BlackJack(CardDeck(), Participants(listOf(Dealer()) + enrollPlayers()))

    private fun enrollPlayers(): List<Player> = InputView.inputNames().map(::Player)

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        OutputView.printFirstOpenCards(blackJack.getFirstOpenCards())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.readyToStart()
    }

    private fun takeTurns(blackJack: BlackJack) {
        blackJack.getPlayers().forEach { player ->
            takePlayerTurn(blackJack, player)
        }
        OutputView.printInterval()
    }

    private fun takePlayerTurn(blackJack: BlackJack, participant: Participant) {
        if (participant.canDraw()) {
            val isDraw = InputView.inputDrawCommand(participant.name)
            if (!isDraw) return printPlayerCards(participant)

            drawCard(blackJack, participant)
            takePlayerTurn(blackJack, participant)
        }
    }

    private fun drawCard(blackJack: BlackJack, participant: Participant) {
        blackJack.draw(participant)
        printPlayerCards(participant)
    }

    private fun printPlayerCards(participant: Participant) {
        OutputView.printCards(mapOf(participant.name to participant.getCards()))
    }

    private fun takeDealerTurn(blackJack: BlackJack) {
        blackJack.drawDealer { isDraw ->
            if (isDraw) OutputView.printDealerDraw()
        }
        OutputView.printInterval()
    }
}
