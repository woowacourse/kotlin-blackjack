package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val players = inputView.readPlayers()
        val dealer = Dealer(players)
        val blackjack = Blackjack(dealer, players)

        outputView.printInitialCardsState(dealer, players)
        inputView.askMoreCards(dealer, players)
        outputView.printFinalCardsScores(dealer, players)
        blackjack.finish()
        outputView.printFinalResults(dealer, players)
    }
}
