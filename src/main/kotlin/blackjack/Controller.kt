package blackjack

import blackjack.model.Dealer
import blackjack.model.DeckManager
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(
) {
    fun run() {
        val names: List<String> = InputView.getNames()
        val players = names.map { Player(it) }
        val dealer = Dealer()
        val deckManager = DeckManager()
        deckManager.initGame(dealer, players)
    }
}
