package controller

import domain.BlackjackGame
import domain.gamer.cards.ParticipantCards
import domain.player.Names
import view.InputView
import view.OutputView

class BlackjackController() {

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
        OutputView.printDivideCard(blackjackGame.names)
        OutputView.printDealerSettingCard(blackjackGame.dealerState.cards[0])
        OutputView.printParticipantsCards(blackjackGame.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame) {
        val playerResult = blackjackGame.getPlayerWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResult)
        OutputView.printWinningResult(dealerResult, playerResult)
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        val cardResult = mutableMapOf<String, ParticipantCards>(DEALER to blackjackGame.dealerState)
        blackjackGame.players.map {
            cardResult.put(it.name, it.state)
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame) {
        blackjackGame.names.userNames.forEach { name ->
            repeatPickCard(blackjackGame, name)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, name: String) {
        while (!blackjackGame.checkBurst(name)!!) {
            val answer = validatePickAnswer(name)
            if (answer) blackjackGame.pickPlayerCard(name) else return
            blackjackGame.players.find { it.name == name }?.state?.let {
                OutputView.printParticipantCards(
                    name,
                    it.cards
                )
            }
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
