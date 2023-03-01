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
        var user = blackJackGame.setUp()
        outputView.outputInitState(blackJackGame.dealer, blackJackGame.users)

        while (blackJackGame.isRunning) {
            val prev = user
            user = blackJackGame.progress(user, inputView.inputDrawMore(user.name))
        }
    }
}
