import controller.Controller
import view.InputView
import view.OutputView

fun main() {
    runCatching {
        val controller = Controller(InputView(), OutputView())
        controller.run()
    }.onFailure {
        println(it.message)
    }
}
