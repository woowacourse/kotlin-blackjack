import blackjack.controller.Casino
import blackjack.domain.generator.ShuffledCardsGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val cardsGenerator = ShuffledCardsGenerator()
    val casino = Casino(inputView, outputView, cardsGenerator)

    casino.run()
}
