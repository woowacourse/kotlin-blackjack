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

        allParticipantsInitialCards(blackjackGame)
        playersDrawCards(blackjackGame)
        dealerDrawCard(blackjackGame)
        allParticipantsCardStatus(blackjackGame)
        resultSummary(blackjackGame)
    }

    private fun allParticipantsInitialCards(blackjackGame: BlackjackGame) {
        blackjackGame.processDistributeInitialCards(
            showDistributeGuideMessage = { players -> outputView.printInitialHandOutCardMessage(players) },
            showDistributedCardStatus = { dealer, players -> outputView.printAllPlayerHands(dealer, players) },
        )
    }

    private fun playersDrawCards(blackjackGame: BlackjackGame) {
        blackjackGame.processPlayerDrawCards(playerDrawDecision = { player: Player ->
            inputView.readCardDrawChoice(player)
        }, showPlayerCardStatus = { player: Player ->
            outputView.printPlayerHands(player)
        })
    }

    private fun dealerDrawCard(blackjackGame: BlackjackGame) {
        blackjackGame.processDealerDrawCard(
            showDealerCardStatus = { isDraw -> outputView.printDealerHandStatus(isDraw) },
        )
    }

    private fun allParticipantsCardStatus(blackjackGame: BlackjackGame) {
        blackjackGame.processAllParticipantsCardStatus(
            showParticipantsCardStatus = { dealer, players ->
                outputView.printFinalHandStatus(dealer, players)
            },
        )
    }

    private fun resultSummary(blackjackGame: BlackjackGame) {
        blackjackGame.processResultSummary(
            showResultSummary = { playerResultSummary, dealerResultSummary ->
                outputView.printFinalResult(playerResultSummary, dealerResultSummary)
            },
        )
    }
}
