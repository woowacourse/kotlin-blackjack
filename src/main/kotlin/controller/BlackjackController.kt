package controller

import domain.BlackjackGame
import domain.participants.Money
import domain.participants.Names
import view.InputView
import view.OutputView

class BlackjackController {

    fun startGame() {
        val names = insertNames()
        val blackjackGame = BlackjackGame(names, this::repeatForRightAnswer)
        OutputView.printBlackjackSetting(blackjackGame.players, blackjackGame.dealer)
        val result = blackjackGame.play(
            InputView::inputRepeatGetCard,
            OutputView::printParticipantCards,
            OutputView::printDealerUnder16
        )
        OutputView.printResult(result.first, result.second)
    }

    private fun insertNames(): Names {
        val names = InputView.inputPlayerNames()
        return runCatching { Names(names) }
            .onFailure { println(it.message) }
            .getOrNull() ?: insertNames()
    }

    private fun repeatForRightAnswer(player: String): Money {
        return runCatching { Money(InputView.inputBetAmount(player)) }
            .onFailure { println(it.message) }
            .getOrNull() ?: repeatForRightAnswer(player)
    }
}
