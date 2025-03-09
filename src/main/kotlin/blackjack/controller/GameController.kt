package blackjack.controller

import blackjack.domain.model.Action
import blackjack.domain.model.Cards
import blackjack.domain.model.Cards.Companion.START_CARD_COUNT
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Cards()
        val dealer = Dealer()
        val players: List<Player> = inputView.readPlayerNames().map(::Player)
        initialDeal(deck, players)
        printInitialDeal(dealer, players)
        players.forEach { player -> playHand(player, deck) }
        processDealerHits(deck, dealer)
        announceResult(dealer, players)
    }

    private fun initialDeal(
        deck: Cards,
        players: List<Player>,
    ) {
        players.forEach { player ->
            player.accept(deck.draw(START_CARD_COUNT))
        }
    }

    private fun printInitialDeal(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printInitialDeals(dealer, players)
        outputView.printParticipantStatus(dealer, players)
    }

    private fun playHand(
        player: Player,
        deck: Cards,
    ) {
        if (player.isBusted()) return
        val action = retryEvent { inputView.readPlayerAction(player) }
        if (action == Action.STAND) {
            printStatusOnNoHit(player)
            return
        }
        player.accept(deck.draw())
        outputView.printPlayerStatus(player)
        playHand(player, deck)
    }

    private fun printStatusOnNoHit(player: Player) {
        if (player.showHand().count() == START_CARD_COUNT) outputView.printPlayerStatus(player)
    }

    private fun processDealerHits(
        deck: Cards,
        dealer: Dealer,
    ) {
        while (dealer.computePoint() <= Dealer.DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.accept(deck.draw())
        }
    }

    private fun announceResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player -> outputView.printPlayerResult(player) }
        val playersResult = dealer.getPlayerResult(players)
        outputView.printResultsHeader()
        val results = dealer.getDealerResults(playersResult)
        outputView.printDealerResults(dealer, results)
        playersResult.forEach { (player, result) -> outputView.printPlayerResult(player, result) }
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
