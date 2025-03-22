package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val deck = Deck()

    fun start() {
        val dealer = Dealer()
        val players = createPlayers()
        dealFirstTurn(dealer, players)
        players.forEach { turnPlayer(it) }
        dealerPlay(dealer)
        printResults(dealer, players)
    }

    private fun createPlayers(): List<Player> {
        val playersName = inputView.readPlayerNames()
        val players =
            playersName.map {
                val bettingAmount = inputView.readBettingAmount(it)
                Player(it, Money(bettingAmount))
            }
        return players
    }

    private fun dealFirstTurn(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.drawTo(deck.draw())
        players.forEach {
            it.drawTo(deck.draw())
            it.drawTo(deck.draw())
        }
        outputView.printDealingResult(dealer, players)
        dealer.drawTo(deck.draw())
    }

    private fun turnPlayer(player: Player) {
        while (player.canDraw() && askDraw(player)) {
            player.drawTo(deck.draw())
            outputView.printPlayerCards(player)
        }
        player.stay()
    }

    private fun askDraw(player: Player): Boolean {
        val answer = inputView.readHitOrStay(player)
        return answer == Action.HIT
    }

    private fun dealerPlay(dealer: Dealer) {
        val countHit = drawUntilStay(dealer)
        if (countHit > 0) {
            outputView.printDealerHit(countHit)
        }
        dealer.stay()
    }

    private fun drawUntilStay(dealer: Dealer): Int {
        var count = 0
        while (dealer.canDraw()) {
            dealer.drawTo(deck.draw())
            count++
        }
        return count
    }

    private fun printResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printBlackjackScore(dealer, players)
        players.forEach { player ->
            player.profit(dealer)
        }
        dealer.calculateProfitWith(players)
        outputView.printDealerProfit(dealer.profit.toInt())
        players.forEach {
            outputView.printPlayerProfit(it, it.profit.toInt())
        }
    }
}
