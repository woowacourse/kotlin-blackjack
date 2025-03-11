package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.START_CARD_COUNT
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
        val participants = initialParticipants(deck)
        printInitialDeal(participants)
        participants.filterPlayers().forEach { player -> playHand(player, deck) }
        processDealerHits(deck, participants.findDealer())
        announceResult(participants)
    }

    private fun initialParticipants(deck: Deck): Participants {
        val playersNames = inputView.readPlayerNames()
        val players =
            playersNames.map { name ->
                Player(Hands(List(START_CARD_COUNT) { deck.draw() }), name)
            }
        val dealer = Dealer(Hands(List(START_CARD_COUNT) { deck.draw() }))
        return Participants(players + dealer)
    }

    private fun printInitialDeal(participants: Participants) {
        outputView.printInitialDeals(participants)
        outputView.printPlayersStatus(participants)
    }

    private fun playHand(
        participant: Participant,
        deck: Deck,
    ) {
        if (participant.isBust()) return
        val choice = retryEvent { inputView.readPlayerAction(participant) }
        if (!choice.isYes()) {
            printStatusOnNoHit(participant)
            return
        }
        participant.acceptCard(deck.draw())
        outputView.printPlayerStatus(participant)
        playHand(participant, deck)
    }

    private fun printStatusOnNoHit(player: Participant) {
        if (player.isStartCardCount()) outputView.printPlayerStatus(player)
    }

    private fun processDealerHits(
        deck: Deck,
        dealer: Dealer,
    ) {
        while (dealer.getScore() <= Dealer.DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.acceptCard(deck.draw())
        }
    }

    private fun announceResult(participants: Participants) {
        val dealer = participants.findDealer()
        val players = participants.filterPlayers()
        outputView.printPlayersResult(participants)
        outputView.printResultsHeader()
        val verdicts = dealer.getDealerVerdicts(players)
        outputView.printDealerVerdicts(dealer, verdicts)
        dealer.getPlayerVerdict(players).forEach { (player, verdict) -> outputView.printPlayerVerdict(player, verdict) }
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
