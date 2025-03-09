package blackjack.controller

import blackjack.domain.model.Action
import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Participant
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Deck()
        val dealer = Dealer()
        val players: List<Player> = inputView.readPlayerNames().map(::Player)
        processInitialDeals(deck, listOf(dealer) + players)
        printInitialDeals(dealer, players)
        players.forEach { player -> processPlayerHits(deck, player) }
        processDealerHits(deck, dealer)
        announceResult(dealer, players)
    }

    private fun processInitialDeals(
        deck: Deck,
        participants: List<Participant>,
    ) {
        participants.forEach { player ->
            player.accept(deck.draw(Participant.INITIAL_DRAW_COUNT))
        }
    }

    private fun printInitialDeals(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printInitialDeals(dealer, players)
        outputView.printParticipantStatus(dealer, players)
    }

    private fun processPlayerHits(
        deck: Deck,
        player: Player,
    ) {
        if (!player.canHit()) return
        val action = inputView.readPlayerAction(player)
        if (action == Action.STAND) {
            printStatusOnNoHit(player)
            return
        }
        player.accept(deck.draw())
        outputView.printPlayerStatus(player)
        processPlayerHits(deck, player)
    }

    private fun printStatusOnNoHit(player: Player) {
        if (player.showHand().count() == Participant.INITIAL_DRAW_COUNT) outputView.printPlayerStatus(player)
    }

    private fun processDealerHits(
        deck: Deck,
        dealer: Dealer,
    ) {
        while (dealer.canHit()) {
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
}
