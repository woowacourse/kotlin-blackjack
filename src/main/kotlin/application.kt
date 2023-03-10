import controller.Controller
import view.InputView
import view.OutputView

fun main() {
    runCatching {
        val outputView = OutputView()
        val inputView = InputView(outputView::printError)
        val controller = Controller(inputView, outputView)
        controller.run()
    }.onFailure {
        println(it.message)
    }
}
