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

        players.forEach { player ->
            while (player.canGetCard()) {
                if (inputView.askMoreCards(player)) {
                    dealer.giveCard(player)
                    println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
                } else {
                    break
                }
            }
        }

        outputView.printFinalCardsScores(dealer, players)
        blackjack.finish()
        outputView.printFinalResults(dealer, players)
    }
}
