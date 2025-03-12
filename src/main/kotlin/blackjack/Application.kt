package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView
import kotlin.system.exitProcess

fun main() {
    runCatching {
        GameController(
            InputView,
            OutputView,
        ).run()
    }.onFailure { exception ->
        exception.message?.let { OutputView.printOnGlobalExceptionOccur(it) }
        exitProcess(0)
    }
}
