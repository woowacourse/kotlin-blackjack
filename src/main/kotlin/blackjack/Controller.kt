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
        OutputView.printInitialResult(dealer, players)

        players.forEach { player ->
            proceedPlayerTurn(player, deckManager)
        }
        proceedDealerTurn(dealer, deckManager)

        OutputView.printResult(dealer, players)
    }

    private fun proceedPlayerTurn(player: Player, deckManager: DeckManager) {
        if (player.isMaxScore()) {
            OutputView.printBlackJackMessage(player)
            return
        }
        while (player.isHitable() && askPick(player.name)) {
            deckManager giveCardTo player
            OutputView.printParticipantStatus(player)
        }
        if (player.isBusted()) {
            OutputView.printBustedMessage(player)
            return
        }
    }

    private fun askPick(name: String): Boolean {
        return InputView.askPickAgain(name)
    }

    private fun proceedDealerTurn(dealer: Dealer, deckManager: DeckManager) {
        while (dealer.isHitable()) {
            deckManager giveCardTo dealer
            OutputView.printDealerHitMessage()
        }
    }

}


fun main() {
    val s = Controller()
    s.run()
}
