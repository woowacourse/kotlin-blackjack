package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play(dealer: Dealer) {
        val players: List<Player> = inputView.readPlayerNames()
        val blackjackGame = BlackjackGame(dealer, players)

        gameReady(blackjackGame)
        gameStart(blackjackGame)
        gameResult(blackjackGame)
    }

    private fun gameReady(blackjackGame: BlackjackGame) {
        blackjackGame.processDistributeInitialCards(
            showDistributeGuideMessage = { players -> outputView.printInitialHandOutCardMessage(players) },
            showDistributedCardStatus = { dealer, players -> outputView.printAllPlayerHands(dealer, players) },
        )
    }

    private fun gameStart(blackjackGame: BlackjackGame) {
        blackjackGame.processParticipantsDrawCards(
            playerDrawDecision = { player: Player -> inputView.readCardDrawChoice(player) },
            showPlayerCardStatus = { player: Player -> outputView.printPlayerHands(player) },
            showDealerCardStatus = { isDraw -> outputView.printDealerHandStatus(isDraw) },
        )
    }

    private fun gameResult(blackjackGame: BlackjackGame) {
        blackjackGame.processAllParticipantsCardStatus(
            showParticipantsCardStatus = { dealer, players -> outputView.printFinalHandStatus(dealer, players) },
        )
        blackjackGame.processResultSummary(
            showResultSummary = { playerResultSummary, dealerResultSummary ->
                outputView.printFinalResult(playerResultSummary, dealerResultSummary)
            },
        )
    }
}
