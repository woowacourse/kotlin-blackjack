import blackjack.controller.Casino
import blackjack.view.InputView
import blackjack.view.OutputView
import java.util.Locale

fun main() {
    val inputView = InputView()
    val outputView = OutputView(Locale.KOREAN)
    val casino = Casino(inputView, outputView)
    casino.gameStart()
}
