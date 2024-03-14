import blackjack.Controller
import blackjack.view.OutputView
import kotlin.system.exitProcess

fun main() {
    val controller = Controller()
    controller.runCatching {
        run()
    }.onFailure {
        OutputView.printError(it.message ?: "")
        exitProcess(0)
    }
}
