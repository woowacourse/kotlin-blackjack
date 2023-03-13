package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.card.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        Blackjack(CardDeck(), InputView.inputPlayers(), OutputView).start()
    }
}
