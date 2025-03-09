package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Deck()
        val participants = Participants(Dealer(), inputView.readPlayerNames())
        initialDeal(deck, participants)
        printInitialDeal(participants)
        participants.filterPlayers().forEach { player -> playHand(player, deck) }
        processDealerHits(deck, participants.findDealer())
        announceResult(participants)
    }

    private fun initialDeal(
        deck: Deck,
        participants: Participants,
    ) {
        participants.players.forEach { player ->
            player.hands.accept(deck.draw(START_CARD_COUNT))
        }
    }

    private fun printInitialDeal(participants: Participants) {
        outputView.printInitialDeals(participants)
        outputView.printPlayersStatus(participants)
    }

    private fun playHand(
        participant: Participant,
        deck: Deck,
    ) {
        if (participant.hands.isBust()) return
        val choice = retryEvent { inputView.readPlayerAction(participant) }
        if (!choice.isYes()) {
            printStatusOnNoHit(participant)
            return
        }
        participant.hands.accept(deck.draw())
        outputView.printPlayerStatus(participant)
        playHand(participant, deck)
    }

    private fun printStatusOnNoHit(player: Participant) {
        if (player.hands.isStartCardCount()) outputView.printPlayerStatus(player)
    }

    private fun processDealerHits(
        deck: Deck,
        dealer: Dealer,
    ) {
        while (dealer.hands.getScore() <= Dealer.DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.hands.accept(deck.draw())
        }
    }

    private fun announceResult(participants: Participants) {
        val dealer = participants.findDealer()
        val players = participants.filterPlayers()
        participants.players.forEach { player -> outputView.printPlayerResult(player) }
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

    companion object {
        private const val START_CARD_COUNT = 2
    }
}
