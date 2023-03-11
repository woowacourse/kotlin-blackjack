package controller

import domain.BlackjackGame
import domain.BlackjackResult
import domain.participants.Money
import domain.participants.Names
import view.InputView
import view.OutputView

class BlackjackController {

    fun startGame() {
        val names = insertNames()
        val blackjackGame = BlackjackGame(names, insertBetAmount(names))
        OutputView.printBlackjackSetting(blackjackGame.players, blackjackGame.dealer)
        blackjackGame.play(
            InputView::inputRepeatGetCard,
            OutputView::printParticipantCards,
            OutputView::printDealerUnder16
        )

        OutputView.printResult(BlackjackResult(blackjackGame.dealer, blackjackGame.players).getResult())
    }

    private fun insertNames(): Names {
        val names = InputView.inputPlayerNames()
        return runCatching { Names(names) }
            .onFailure { println(it.message) }
            .getOrNull() ?: insertNames()
    }

    private fun insertBetAmount(names: Names): List<Money> {
        return names.userNames.map {
            repeatForRightAnswer(it)
        }
    }

    private fun repeatForRightAnswer(player: String): Money {
        return runCatching { Money(InputView.inputBetAmount(player)) }
            .onFailure { println(it.message) }
            .getOrNull() ?: repeatForRightAnswer(player)
    }
}
