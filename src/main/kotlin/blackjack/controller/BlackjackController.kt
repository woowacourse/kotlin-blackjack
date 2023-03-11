package blackjack.controller

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun run() {
        val blackjackGame = BlackjackGame(participants = inputView.readParticipants())
        blackjackGame.setFirstTurnPlayersCards(outputView::printFirstTurnSettingCards)
        blackjackGame.hitPlayersCards(
            inputView::readHitOrNot,
            outputView::printPlayerCards,
            outputView::printDealerHitOrNotMessage
        )
        blackjackGame.decidePlayersResult()

        printResult(blackjackGame.dealer, blackjackGame.participants)
    }

    private fun printResult(dealer: Dealer, participants: Participants) {
        outputView.printSumResult(dealer, participants)
        outputView.printPlayersResults(dealer, participants)
    }
}
