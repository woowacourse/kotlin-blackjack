import domain.BlackJackGameController
import view.InputView
import view.ResultView

fun main() {
    val controller = BlackJackGameController(InputView(), ResultView())
    controller.run()
}
