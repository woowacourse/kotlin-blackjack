package blackjack.controller

import blackjack.domain.Participants
import blackjack.domain.carddeck.CardDeck
import blackjack.domain.carddeck.cardnumbergenerator.RandomCardNumberGenerator
import blackjack.domain.carddeck.shapegenerator.RandomShapeGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    fun runGame() {
        val participants =
            Participants(InputView.getPlayerNames(), CardDeck(RandomShapeGenerator(), RandomCardNumberGenerator()))
        showInitialState(participants)
        participants.progressPlayersAddCard(InputView::getDecision, OutputView::printPlayerCards)
        printResult(participants)
    }

    private fun printResult(participants: Participants) {
        progressDealerAddCard(participants)
        printTotalScore(participants)
        printWinOrLose(participants)
    }

    private fun showInitialState(participants: Participants) {
        OutputView.printDistributeScript(participants.players)
        OutputView.printDealerInitialCard(participants.dealer.cardBunch)
        participants.players.forEach { OutputView.printPlayerCards(it) }
    }

    private fun progressDealerAddCard(participants: Participants) {
        OutputView.printDealerOverCondition(!participants.dealer.isOverCondition())
        participants.judgmentDealerAddCard()
    }

    private fun printTotalScore(participants: Participants) {
        OutputView.printTotalScore(participants)
    }

    private fun printWinOrLose(participants: Participants) {
        OutputView.printWinOrLose(participants)
    }
}
