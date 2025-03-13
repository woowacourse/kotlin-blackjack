package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Hand
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
        val participants = initializeParticipants(deck)
        participants.processPlayerBets(inputView::readPlayerBet)
        processHits(deck, participants)
        outputView.printResults(participants)
    }

    private fun initializeParticipants(deck: Deck): Participants {
        val dealer = Dealer(deck.draw(Hand.STARTING_HAND_SIZE))
        val players =
            repeatUntilValid {
                inputView.readPlayerNames().map { playerName -> Player(playerName, deck.draw(Hand.STARTING_HAND_SIZE)) }
            }
        val participants = Participants(dealer, players)
        outputView.printInitialDeals(participants)
        participants.all.forEach { participant -> outputView.printParticipantStatus(participant) }
        return Participants(dealer, players)
    }

    private fun processHits(
        deck: Deck,
        participants: Participants,
    ) {
        participants.processPlayersHits(
            deck,
            { player -> repeatUntilValid { inputView.readPlayerAction(player) } },
            outputView::printParticipantStatus,
        )
        participants.processDealerHits(deck, outputView::printDealerHit)
    }

    private fun <T> repeatUntilValid(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { println(it.message ?: it.stackTraceToString()) }
        }
    }
}
