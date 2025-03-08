package blackjack

import blackjack.controller.BlackJackController
import blackjack.domain.card.CardFactoryImpl
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val cardFactory = CardFactoryImpl()

    BlackJackController(inputView, outputView, cardFactory).run()
}
