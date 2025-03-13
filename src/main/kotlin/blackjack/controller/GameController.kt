package blackjack.controller

import blackjack.domain.model.BetAmount
import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.START_CARD_COUNT
import blackjack.domain.model.Money
import blackjack.domain.model.Participant
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.domain.model.Profit
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
        val playersBetAmount =
            participants.players.associateWith {
                val money = Money(inputView.readPlayerBetAmount(it.name))
                BetAmount(money)
            }
        participants.players.forEach { player -> playHand(player, deck) }
        processDealerHits(deck, participants.dealer)
        announceResult(participants, playersBetAmount)
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

    private fun announceResult(
        participants: Participants,
        playersBetAmount: Map<Player, BetAmount>,
    ) {
        outputView.printParticipantsResult(participants)
        outputView.printResultsHeader()
        val playersProfit = initPlayersProfit(participants, playersBetAmount)
        val dealerProfit = playersProfit.values.sumOf { it.value } * -1
        outputView.printDealerProfit(participants.dealer.name, dealerProfit)
        outputView.printPlayersProfit(playersProfit)
    }

    private fun initPlayersProfit(
        participants: Participants,
        playersBetAmount: Map<Player, BetAmount>,
    ): Map<Player, Profit> {
        val verdict = Verdict(participants.dealer)
        return participants.players.associateWith {
            playersBetAmount[it]?.calculate(verdict.determine(it)) ?: Profit(0)
        }
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
