package controller

import domain.BlackjackGame
import domain.gamer.state.ParticipantState
import view.InputView
import view.OutputView

class BlackjackController() {

    fun startGame() {
        val names = InputView.inputPlayerNames()
        val blackjackGame = BlackjackGame(names)
        printBlackjackSetting(names, blackjackGame)
        requestPickCard(names, blackjackGame)
        dealerPickCard(blackjackGame)
        printCardResult(blackjackGame)
        printWinningResult(blackjackGame)
    }

    private fun printBlackjackSetting(names: List<String>, blackjackGame: BlackjackGame) {
        OutputView.printDivideCard(names)
        OutputView.printDealerSettingCard(blackjackGame.dealerState.cards[0])
        OutputView.printParticipantsCards(blackjackGame.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame) {
        val playerResult = blackjackGame.getPlayerWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResult)
        OutputView.printWinningResult(dealerResult, playerResult)
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        val cardResult = mutableMapOf<String, ParticipantState>(DEALER to blackjackGame.dealerState)
        blackjackGame.players.map {
            cardResult.put(it.name, it.state)
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(names: List<String>, blackjackGame: BlackjackGame) {
        names.forEach { name ->
            repeatPickCard(blackjackGame, name)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, name: String) {
        while (!blackjackGame.checkBurst(name)!!) {
            val answer = validatePickAnswer(name)
            if (answer) blackjackGame.pickPlayerCard(name) else return
            blackjackGame.players.find { it.name == name }?.state?.let { OutputView.printParticipantCards(name, it.cards) }
        }
    }

    private fun validatePickAnswer(name: String): Boolean {
        val answer = InputView.inputRepeatGetCard(name)
        return answer ?: validatePickAnswer(name)
    }

    private fun dealerPickCard(blackjackGame: BlackjackGame) {
        if (blackjackGame.checkDealerAvailableForPick()) {
            OutputView.printDealerUnder16()
            blackjackGame.pickDealerCard()
        }
    }

    companion object {
        private const val DEALER = "딜러"
    }
}
