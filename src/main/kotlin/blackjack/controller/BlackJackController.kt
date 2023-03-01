package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun run() {
        val cardDeck = CardDeck(Card.all())
        cardDeck.shuffle()
        val names = inputView.inputParticipants()
        val blackJackGame = BlackJackGame(names)
    }
}
