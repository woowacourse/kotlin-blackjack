package blackjack

import blackjack.controller.BlackJackController
import blackjack.domain.card.cardFactoryImpl
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val cardFactory = cardFactoryImpl()
    BlackJackController(inputView, outputView, cardFactory).run()
}
