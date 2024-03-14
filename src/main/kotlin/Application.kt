import blackjack.Controller
import blackjack.model.CardMaker

fun main() {
    val cards = CardMaker().shuffledCards
    val controller = Controller(cards)
    controller.run()
}
