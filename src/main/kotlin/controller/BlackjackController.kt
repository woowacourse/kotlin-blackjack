package controller

import domain.BlackjackGame
import domain.participants.Names
import view.InputView
import view.OutputView

class BlackjackController {

    fun startGame() {
        val names = insertNames()
        val blackjackGame = BlackjackGame(names)
        printBlackjackSetting(blackjackGame)
        requestPickCard(blackjackGame)
        dealerPickCard(blackjackGame)
        printCardResult(blackjackGame)
        printWinningResult(blackjackGame)
    }

    private fun insertNames(): Names {
        val names = InputView.inputPlayerNames()
        return runCatching { Names(names) }
            .onFailure { println(it.message) }
            .getOrNull() ?: insertNames()
    }

    private fun printBlackjackSetting(blackjackGame: BlackjackGame) {
        OutputView.printDivideCard(blackjackGame.players)
        OutputView.printDealerSettingCard(blackjackGame.dealer)
        OutputView.printParticipantsCards(blackjackGame.players)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach { player ->
            blackjackGame.repeatPickCard(
                player.name,
                { validatePickAnswer(player.name) },
                (OutputView::printParticipantCards)
            )
        }
    }

    private fun validatePickAnswer(name: String): Boolean {
        val answer = InputView.inputRepeatGetCard(name)
        return answer ?: validatePickAnswer(name)
    }

    private fun dealerPickCard(blackjackGame: BlackjackGame) {
        if (blackjackGame.pickDealerCardIfPossible())
            OutputView.printDealerUnder16()
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        OutputView.printCardResult(blackjackGame.dealer, blackjackGame.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame) {
        val playerResult = blackjackGame.getPlayerWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResult)
        OutputView.printWinningResult(dealerResult, playerResult)
    }

    companion object {
        private const val DEALER = "딜러"
    }
}
