package controller

import domain.BlackjackGame
import domain.gamer.Participant
import domain.gamer.Player
import view.InputView
import view.OutputView

class BlackjackController() {

    fun startGame() {
        val names = InputView.inputPlayerNames()
        val blackjackGame = BlackjackGame(names)
        blackjackGame.startGame()
        printBlackjackSetting(names, blackjackGame)
        requestPickCard(blackjackGame)
        dealerPickCard(blackjackGame)
        printCardResult(blackjackGame)
        printWinningResult(blackjackGame)
    }

    private fun printBlackjackSetting(names: List<String>, blackjackGame: BlackjackGame) {
        OutputView.printDivideCard(names)
        OutputView.printDealerSettingCard(blackjackGame.dealer.cards.getCards()[0])
        OutputView.printParticipantsCards(blackjackGame.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame) {
        val playerResult = blackjackGame.getPlayerWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResult)
        OutputView.printWinningResult(dealerResult, playerResult)
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        val cardResult = mutableMapOf<String, Participant>(DEALER to blackjackGame.dealer)
        blackjackGame.players.map {
            cardResult.put(it.name, it)
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach {
            repeatPickCard(blackjackGame, it)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, player: Player) {
        while (!blackjackGame.checkBurst(player)) {
            val answer = validatePickAnswer(player.name)
            if (answer == YES_ANSWER) blackjackGame.pickPlayerCard(player)
            if (answer == NO_ANSWER) return
            OutputView.printParticipantCards(player.name, player.cards)
        }
    }

    private fun validatePickAnswer(name: String): String {
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
        const val YES_ANSWER = "y"
        const val NO_ANSWER = "n"
    }
}
