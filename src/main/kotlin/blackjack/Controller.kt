package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.DealingShoe
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(cards: List<Card>) {
    private val dealingShoe = DealingShoe(cards)

    fun run() {
        val players = makePlayers()
        val dealer = Dealer()
        runCatching {
            play(dealer, players)
        }.onSuccess {
            printGameResult(dealer, players)
            printAmountResult(dealer, players)
        }.onFailure {
            OutputView.printExceptionMessage(it.message)
        }
    }

    private fun makePlayers(): Players {
        val names: List<String> = InputView.getNames()
        val betAmounts =
            names.map { InputView.getBetAmount(it) }
        return Players.of(names, betAmounts)
    }

    private fun play(
        dealer: Dealer,
        players: Players,
    ) {
        initCard(dealer, players)
        takeTurn(dealer, players)
    }

    private fun initCard(
        dealer: Dealer,
        players: Players,
    ) {
        dealer.pickCard(dealingShoe, 2)
        players.initCard(dealingShoe)
        OutputView.printInitialResult(dealer, players.players)
    }

    private fun takeTurn(
        dealer: Dealer,
        players: Players,
    ) {
        players.hitOrStay(dealingShoe, InputView::askPickAgain, OutputView::printCards)
        dealer.hitOrStay(dealingShoe, OutputView::printDealerHitMessage)
    }

    private fun printGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.printResult(dealer, players.players)
        OutputView.printFinalBetAmountMessage()
    }

    private fun printAmountResult(
        dealer: Dealer,
        players: Players,
    ) {
        val amountStatistics = players.getAmountStatistics(dealer)
        OutputView.printAmountResult(amountStatistics)
    }
}
