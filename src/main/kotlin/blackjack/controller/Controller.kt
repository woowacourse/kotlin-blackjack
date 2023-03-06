package blackjack.controller

import blackjack.domain.CardBunch
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.carddeck.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(private val cardDeck: CardDeck) {
    fun runGame() {
        val dealer = makeDealer()
        val players = makePlayers(InputView.getPlayerNames())
        showInitialCard(dealer, players)
        players.forEach { player -> askGetCard(player) }
        printCardAndScore(dealer, players)
        printFinalWinOrLose(dealer, players)
    }

    private fun printCardAndScore(dealer: Dealer, players: List<Player>) {
        showDealerState(dealer)
        OutputView.printCardAndScore(dealer, players)
    }

    private fun makePlayers(names: List<String>): List<Player> =
        names.map { Player(it, makeInitialCardBunch()) }

    private fun makeDealer(): Dealer = Dealer(makeInitialCardBunch())

    private fun makeInitialCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())

    private fun showInitialCard(dealer: Dealer, players: List<Player>) {
        OutputView.printDistributeScript(players)
        OutputView.printDealerInitialCard(dealer.cardBunch)
        players.forEach { OutputView.printPlayerCard(it) }
    }

    private fun askGetCard(player: Player) {
        while (player.canGetCard()) {
            if (!isSuccessAddCardToPlayer(player)) return
        }
    }

    private fun isSuccessAddCardToPlayer(player: Player): Boolean {
        if (InputView.getDecision(player)) {
            player.receiveCard(cardDeck.drawCard())
            OutputView.printPlayerCard(player)
            return true
        }
        OutputView.printPlayerCard(player)
        return false
    }

    private fun showDealerState(dealer: Dealer) {
        val condition = dealer.canGetCard()
        OutputView.printDealerOverCondition(condition)
        if (condition) dealer.receiveCard(cardDeck.drawCard())
    }

    private fun printFinalWinOrLose(dealer: Dealer, players: List<Player>) {
        val gameResult = dealer.versusPlayers(players)
        OutputView.printWinOrLose(gameResult)
    }
}
