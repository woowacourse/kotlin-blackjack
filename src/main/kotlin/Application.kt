import controller.BlackjackController
import model.card.CardsGenerator
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val cardsGenerator = CardsGenerator()

    BlackjackController(inputView, outputView, cardsGenerator).run()
}
