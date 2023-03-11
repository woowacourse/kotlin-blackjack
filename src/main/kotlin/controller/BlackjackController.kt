package controller

import domain.BlackjackGame
import domain.BlackjackResult
import domain.participants.Names
import view.InputView
import view.OutputView

class BlackjackController {

    fun startGame() {
        val names = insertNames()
        val blackjackGame = BlackjackGame(names, InputView.inputBetAmount(names))
        OutputView.printBlackjackSetting(blackjackGame.players, blackjackGame.dealer)
        blackjackGame.play(InputView::inputRepeatGetCard, OutputView::printParticipantCards, OutputView::printDealerUnder16)

        OutputView.printResult(BlackjackResult(blackjackGame.dealer, blackjackGame.players).getResult())
    }

    private fun insertNames(): Names {
        val names = InputView.inputPlayerNames()
        return runCatching { Names(names) }
            .onFailure { println(it.message) }
            .getOrNull() ?: insertNames()
    }
}
