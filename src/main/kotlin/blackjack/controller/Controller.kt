package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.carddeck.RandomCardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    fun runGame() {
        val blackJackGame = BlackJackGame(InputView.getPlayerNames(), RandomCardDeck)
        showInitialState(blackJackGame)
        blackJackGame.progressPlayersAddCard(InputView::getDecision, OutputView::printPlayerCards)
        printResult(blackJackGame)
    }

    private fun printResult(blackJackGame: BlackJackGame) {
        progressDealerAddCard(blackJackGame)
        printTotalScore(blackJackGame)
        printWinOrLose(blackJackGame)
    }

    private fun showInitialState(blackJackGame: BlackJackGame) {
        OutputView.printDistributeScript(blackJackGame.participants.players.value)
        OutputView.printDealerInitialCard(blackJackGame.participants.dealer.cardBunch)
        blackJackGame.participants.players.value.forEach { OutputView.printPlayerCards(it) }
    }

    private fun progressDealerAddCard(blackJackGame: BlackJackGame) {
        OutputView.printDealerOverCondition(!blackJackGame.participants.dealer.isOverCondition())
        blackJackGame.progressDealerAddCard()
    }

    private fun printTotalScore(blackJackGame: BlackJackGame) {
        OutputView.printTotalScore(blackJackGame.participants)
    }

    private fun printWinOrLose(blackJackGame: BlackJackGame) {
        OutputView.printWinOrLose(blackJackGame.participants)
    }
}
