package controller

import domain.BlackjackGame
import domain.player.Names
import domain.player.Player
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
        OutputView.printDivideCard(blackjackGame.names)
        OutputView.printDealerSettingCard(blackjackGame.dealerState.cards.first())
        OutputView.printParticipantsCards(blackjackGame.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame) {
        val playerResult = blackjackGame.getPlayerWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResult)
        OutputView.printWinningResult(dealerResult, playerResult)
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        val cardResult = mutableListOf(Player(DEALER, blackjackGame.dealerState))
        blackjackGame.players.forEach {
            cardResult.add(Player(it.name, it.ownCards))
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame) {
        blackjackGame.names.userNames.forEach { name ->
            repeatPickCard(blackjackGame, name)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, name: String) {
        while (!blackjackGame.checkBurst(name)) {
            val answer = validatePickAnswer(name)
            if (answer) blackjackGame.pickPlayerCard(name) else return
            OutputView.printParticipantCards(
                name,
                blackjackGame.findPlayer(name).ownCards.cards
            )
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
