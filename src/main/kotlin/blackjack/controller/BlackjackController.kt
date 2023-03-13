package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun run() {
        val blackjackGame = BlackjackGame(participants = inputView.readParticipants())
        blackjackGame.bettingParticipants(inputView::readParticipantBattingAmount)
        blackjackGame.setFirstTurnPlayersCards(outputView::printFirstTurnSettingCards)
        blackjackGame.hitPlayersCards(
            inputView::readHitOrNot,
            outputView::printPlayerCards,
            outputView::printDealerHitOrNotMessage
        )
        blackjackGame.decidePlayersResult()
        blackjackGame.printGameResult(outputView::printSumResult, outputView::printPlayersResults)
    }
}
