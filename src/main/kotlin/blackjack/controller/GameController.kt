package blackjack.controller

import blackjack.domain.model.Bets
import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.HandState
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
        val bets = participants.bets { name -> inputView.readPlayerBetAmount(name) }
        printInitialDeal(participants)
        participants.players.forEach { player -> playHand(player, deck) }
        processDealerHits(deck, participants.dealer)
        announceParticipantsResult(participants)
        announceResult(participants.dealer, bets)
    }

    private fun initialParticipants(deck: Deck): Participants {
        val playersNames = inputView.readPlayerNames()
        val players =
            playersNames.map { name ->
                Player(Hands(List(START_CARD_COUNT) { deck.draw() }), name)
            }
        val dealer = Dealer(Hands(List(START_CARD_COUNT) { deck.draw() }))
        return Participants(dealer, players)
    }

    private fun printInitialDeal(participants: Participants) {
        outputView.printInitialDeals(participants)
        outputView.printParticipantsStatus(participants)
    }

    private fun playHand(
        participant: Participant,
        deck: Deck,
    ) {
        if (participant.getHandsState() != HandState.HIT) return
        val choice = retryEvent { inputView.readPlayerAction(participant) }
        if (HandState.STAY == choice) {
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
        while (dealer.isHit()) {
            outputView.printDealerHitsState()
            dealer.acceptCard(deck.draw())
        }
    }

    private fun announceParticipantsResult(participants: Participants) {
        outputView.printParticipantsResult(participants)
    }

    private fun announceResult(
        dealer: Dealer,
        bets: Bets,
    ) {
        outputView.printResultsHeader()
        val playersProfit = bets.getProfits(dealer)
        val dealerProfit = playersProfit.calculateTotalLosses(dealer.name)
        outputView.printDealerProfit(dealerProfit)
        outputView.printPlayersProfit(playersProfit)
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
