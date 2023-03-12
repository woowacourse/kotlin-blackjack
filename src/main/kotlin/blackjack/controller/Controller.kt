package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Participants
import blackjack.domain.carddeck.RandomCardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    fun runGame() {
        val blackJackGame = BlackJackGame(InputView.getPlayerNames(), RandomCardDeck, InputView::getBettingAmount)
        showInitialState(blackJackGame.participants)
        blackJackGame.progressPlayersAddCard(InputView::getDecision, OutputView::printPlayerCards)
        blackJackGame.progressDealerAddCard(OutputView::printDealerOverCondition)
        printResult(blackJackGame)
    }

    private fun showInitialState(participants: Participants) {
        OutputView.printDistributeScript(participants.players.value)
        OutputView.printDealerInitialCard(participants.dealer.state.hand)
        OutputView.printAllPlayerCard(participants.players.value)
    }

    private fun printResult(blackJackGame: BlackJackGame) {
        OutputView.printTotalScore(blackJackGame.participants)
    }
}
