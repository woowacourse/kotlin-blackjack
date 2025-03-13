package blackjack.controller

import blackjack.domain.model.Bet
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
        participants.processPlayerBets { player -> retryOnError { Bet(inputView.readPlayerBet(player)) } }
        outputView.printInitialDeals(participants)
        participants.all.forEach { participant -> outputView.printParticipantStatus(participant) }
        processHits(deck, participants)
        outputView.printResults(participants)
    }

    private fun initializeParticipants(deck: Deck): Participants {
        val participants =
            retryOnError {
                Participants(
                    Dealer(deck.draw(Hand.STARTING_HAND_SIZE)),
                    inputView.readPlayerNames().map { playerName ->
                        Player(playerName, deck.draw(Hand.STARTING_HAND_SIZE))
                    },
                )
            }
        return participants
    }

    private fun processHits(
        deck: Deck,
        participants: Participants,
    ) {
        participants.processPlayersHits(
            deck,
            { player -> retryOnError { inputView.readPlayerAction(player) } },
            outputView::printParticipantStatus,
        )
        participants.processDealerHits(deck, outputView::printDealerHit)
    }

    private fun <T> retryOnError(function: () -> T): T {
        return runCatching { function() }.getOrElse { error ->
            println(error.message)
            retryOnError(function)
        }
    }
}
