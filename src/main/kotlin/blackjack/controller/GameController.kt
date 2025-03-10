package blackjack.controller

import blackjack.domain.model.Action
import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Deck()
        val participants = repeatUntilValid { Participants(Dealer(), inputView.readPlayerNames().map(::Player)) }
        processInitialDeals(deck, participants)
        outputView.printInitialDeals(participants)
        outputView.printParticipantsStatuses(participants)
        processPlayersHits(deck, participants.players)
        processDealerHits(deck, participants.dealer)
        outputView.printResults(participants)
    }

    private fun processInitialDeals(
        deck: Deck,
        participants: Participants,
    ) {
        participants.list.forEach { player ->
            player.accept(deck.draw(Participant.INITIAL_DRAW_COUNT))
        }
    }

    private fun processPlayersHits(
        deck: Deck,
        players: List<Player>,
    ) {
        players.forEach { player -> processPlayerHits(deck, player) }
    }

    private fun processPlayerHits(
        deck: Deck,
        player: Player,
    ) {
        if (!player.canHit()) return
        val action = repeatUntilValid { inputView.readPlayerAction(player) }
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
            outputView.printDealerHit()
            dealer.accept(deck.draw())
        }
    }

    private fun <T> repeatUntilValid(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }.onSuccess { return it }
                .onFailure { println(it.message ?: it.stackTraceToString()) }
        }
    }
}
