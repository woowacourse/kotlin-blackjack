import controller.BlackJackController
import view.RequestView
import view.ResultView

fun main() {
    BlackJackController(RequestView(), ResultView()).runBlackJack()
}
