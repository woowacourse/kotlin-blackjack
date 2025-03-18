package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
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
        getBettingAmount(players)
        dealFirstTurn(dealer, players)
        players.forEach {
            turnPlayer(it)
        }
        dealerPlay(dealer)
        printResults(dealer, players)
    }

    private fun createPlayers(): List<Player> {
        val playersName = inputView.readPlayerNames()
        val players = playersName.map { Player(it) }
        return players
    }


    private fun getBettingAmount(players: List<Player>) {
        players.forEach { player ->
            setBettingAmount(player)
        }
    }

    private fun setBettingAmount(player: Player) {
        runCatching {
            val bettingAmount = inputView.readBettingAmount(player.name)
            player.bet(bettingAmount)
        }.onFailure { error ->
            outputView.printError(error)
            setBettingAmount(player)
        }
    }

    private fun dealFirstTurn(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.state.draw(deck.draw())
        players.forEach { it.state = it.state.draw(deck.draw()) }
        players.forEach { it.state = it.state.draw(deck.draw()) }
        outputView.printDealingResult(dealer, players)
    }

    private fun turnPlayer(it: Player) {
        if (it.state.hand.isBust()) {
            outputView.printBust(it)
            return
        }
        val answer = inputView.readHitOrStay(it)
        if (answer == Action.HIT && it.state !is Bust) {
            it.state = it.state.draw(deck.draw())
            outputView.printPlayerCards(it)
        }
        if (it.state is Bust) {
            outputView.printBust(it)
            return
        }
        if (answer == Action.STAY && it.state is Hit) {
            it.state = (it.state as Hit).stay()
            return
        }
        return turnPlayer(it)
    }


    private fun dealerPlay(dealer: Dealer) {
        var count = 0
        while (dealer.state.hand.sum() < 17) {
            count++
            dealer.state = dealer.state.draw(deck.draw())
        }
        outputView.printDealerHit(count)
    }

    private fun printResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printBlackjackScore(dealer, players)
        var dealerProfit = 0.0
        players.forEach {
            val playerProfit = it.profit(dealer)
            dealerProfit -= playerProfit
        }
        outputView.printDealerProfit(dealerProfit.toInt())
        players.forEach {
            val playerProfit = it.profit(dealer)
            outputView.printPlayerProfit(it, playerProfit.toInt())
        }
    }
}
