package blackjack.controller

import blackjack.domain.Calculator
import blackjack.domain.CardBunch
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(private val cardDeck: CardDeck) {
    fun runGame() {
        val dealer = makeDealer()
        val players = makePlayers()
        showInitialCard(dealer, players)
        players.forEach { player -> askHitPlayer(player) }
        printCardAndScore(dealer, players)
        printFinalProfit(Calculator().calculateDividend(dealer.versusPlayers(players)))
    }

    private fun printCardAndScore(dealer: Dealer, players: List<Player>) {
        askHitDealer(dealer)
        OutputView.printCardAndScore(dealer, players)
    }

    private fun makePlayers(): List<Player> {
        val names = InputView.getPlayerNames()
        return names.map { name -> Player(name, makeInitialCardBunch(), InputView.getBettingMoney(name)) }
    }

    private fun makeDealer(): Dealer = Dealer(makeInitialCardBunch())

    private fun makeInitialCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())

    private fun showInitialCard(dealer: Dealer, players: List<Player>) {
        OutputView.printDistributeScript(players)
        OutputView.printDealerInitialCard(dealer.cardBunch)
        players.forEach { OutputView.printPlayerCard(it) }
    }

    private fun askHitPlayer(player: Player) {
        while (player.canHit()) {
            if (!InputView.getHitOrNot(player)) break
            player.receiveCard(cardDeck.drawCard())
            OutputView.printPlayerCard(player)
        }
        OutputView.printPlayerCard(player)
    }

    private fun askHitDealer(dealer: Dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerState(dealer.canHit())
            dealer.receiveCard(cardDeck.drawCard())
        }
        OutputView.printDealerState(dealer.canHit())
    }

    private fun printFinalProfit(playerProfitBy: Map<Player, Int>) {
        OutputView.printProfit(playerProfitBy)
    }
}
