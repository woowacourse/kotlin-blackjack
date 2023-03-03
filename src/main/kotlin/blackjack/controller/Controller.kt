package blackjack.controller

import blackjack.domain.CardBunch
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.carddeck.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(private val cardDeck: CardDeck) {
    fun runGame() {
        val dealer = getDealer()
        val players = getPlayers(InputView.getPlayerNames())
        showInitialState(dealer, players)
        players.forEach { askGetCard(it) }
        showDealerState(dealer)
        printTotalScore(dealer, players)
        players.forEach { dealer.compareScore(it) }
        printResult(players)
    }

    private fun getPlayers(names: List<String>): List<Player> =
        names.map { Player(it, makeCardBunch()) }

    private fun getDealer(): Dealer = Dealer(makeCardBunch())

    private fun makeCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())

    private fun showInitialState(dealer: Dealer, players: List<Player>) {
        OutputView.printDistributeScript(players)
        OutputView.printDealerInitialCard(dealer.cardBunch)
        players.forEach { OutputView.printPlayerCards(it) }
    }

    private fun askGetCard(player: Player) {
        if (!player.cardBunch.isBurst()) {
            if (InputView.getDecision(player)) {
                player.cardBunch.addCard(cardDeck.drawCard())
                OutputView.printPlayerCards(player)
            } else {
                OutputView.printPlayerCards(player)
                return
            }
        }

        if (!player.cardBunch.isBurst()) {
            askGetCard(player)
        }
    }

    private fun showDealerState(dealer: Dealer) {
        val condition = dealer.isOverCondition()
        OutputView.printDealerOverCondition(!condition)
        if (!condition) dealer.cardBunch.addCard(cardDeck.drawCard())
    }

    private fun printTotalScore(dealer: Dealer, players: List<Player>) {
        OutputView.printTotalScore(dealer, players)
    }

    private fun printResult(players: List<Player>) {
        OutputView.printResult(players)
    }
}
