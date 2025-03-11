package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.START_CARD_COUNT
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.domain.model.Verdict
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
        while (dealer.isHit()) {
            outputView.printDealerHitsState()
            dealer.acceptCard(deck.draw())
        }
    }

    private fun announceResult(participants: Participants) {
        outputView.printParticipantsResult(participants)
        outputView.printResultsHeader()
        val dealer = participants.findDealer()
        val players = participants.filterPlayers()
        initVerdict(dealer, players)
        outputView.printDealerVerdicts(dealer)
        players.forEach { player -> outputView.printPlayerVerdict(player) }
    }

    private fun initVerdict(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val verdict = Verdict(dealer)
        val dealerResult =
            players.map { player ->
                val playerVerdictResult = verdict.determine(player)
                player.recordVerdict(playerVerdictResult)
                playerVerdictResult.reverse()
            }
        dealer.recordVerdict(dealerResult)
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
