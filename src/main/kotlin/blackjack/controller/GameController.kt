package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
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
        participants.makeInitialDeals(deck)
        outputView.printInitialDeals(participants)
        participants.list.forEach { participant -> outputView.printParticipantStatus(participant) }
        participants.processPlayersHits(
            deck,
            { player -> repeatUntilValid { inputView.readPlayerAction(player) } },
            outputView::printParticipantStatus,
        )
        participants.processDealerHits(deck, outputView::printDealerHit)
        outputView.printResults(participants)
    }

    private fun <T> repeatUntilValid(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { println(it.message ?: it.stackTraceToString()) }
        }
    }
}
