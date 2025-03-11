package blackjack.controller

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
        repeatUntilValid {
            participants.processPlayersHits(
                deck,
                inputView::readPlayerAction,
                outputView::printParticipantStatus,
            )
        }
        participants.processDealerHits(deck, outputView::printDealerHit)
        outputView.printResults(participants)
    }

    private fun processInitialDeals(
        deck: Deck,
        participants: Participants,
    ) {
        participants.list.forEach { player -> player.accept(deck.draw(Participant.INITIAL_DRAW_COUNT)) }
        outputView.printInitialDeals(participants)
        participants.list.forEach { participant -> outputView.printParticipantStatus(participant) }
    }

    private fun <T> repeatUntilValid(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { println(it.message ?: it.stackTraceToString()) }
        }
    }
}
