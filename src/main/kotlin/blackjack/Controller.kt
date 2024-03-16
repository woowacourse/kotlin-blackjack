package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.DealingShoe
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(cards: List<Card>) {
    private val dealingShoe = DealingShoe(cards)

    fun run() {
        val players = makePlayers()
        val dealer = Dealer()
        try {
            play(dealer, players)
        } catch (e: IllegalStateException) {
            OutputView.printExceptionMessage(e)
        }
    }

    private fun makePlayers(): List<Player> {
        val names: List<String> = InputView.getNames()
        return names.map { name ->
            val amount = InputView.getBetAmount(name)
            Player(name, amount)
        }
    }

    private fun play(
        dealer: Dealer,
        players: List<Player>,
    ) {
        initParticipantsCard(dealer, players)
        proceedParticipantsTure(dealer, players)
        printStatistics(dealer, *players.toTypedArray())
    }

    private fun initParticipantsCard(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.pickCard(dealingShoe, 2)
        players.forEach { player ->
            player.pickCard(dealingShoe, 2)
        }
        OutputView.printInitialResult(dealer, players)
    }

    private fun proceedParticipantsTure(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            proceedPlayerTurn(player)
        }
        proceedDealerTurn(dealer)
    }

    private fun proceedPlayerTurn(player: Player) {
        if (player.isMaxScore()) {
            OutputView.printBlackJackMessage(player)
            return
        }
        while (player.isHittable() && askPick(player.name)) {
            player.pickCard(dealingShoe)
            OutputView.printParticipantStatus(player)
        }
        if (player.isBusted()) {
            OutputView.printBustedMessage(player)
        }
    }

    private fun askPick(name: String): Boolean {
        return InputView.askPickAgain(name)
    }

    private fun proceedDealerTurn(dealer: Dealer) {
        while (dealer.isHittable()) {
            dealer.pickCard(dealingShoe)
            OutputView.printDealerHitMessage()
        }
    }

    private fun printStatistics(
        dealer: Dealer,
        vararg players: Player,
    ) {
        var dealerBetAmount: Long = 0
        players.forEach { player ->
            val playerBetAmount = player.calculateBetAmount(dealer)
            OutputView.printBetAmount(player.name, playerBetAmount)
            dealerBetAmount -= playerBetAmount
        }
        OutputView.printBetAmount(dealer.name, dealerBetAmount)
    }
}
