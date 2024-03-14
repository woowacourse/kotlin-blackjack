package blackjack

import blackjack.controller.BlackJackController
import blackjack.model.dispatcher.Dispatcher
import blackjack.model.store.Store

fun main() {
    val dispatcher = Dispatcher()
    val store = Store(dispatcher)
    val blackJackController = BlackJackController(dispatcher, store)
    blackJackController.startGame()
}
