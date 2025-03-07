package blackjack.controller

import blackjack.domain.model.Cards
import blackjack.domain.model.Cards.Companion.START_CARD_COUNT
import blackjack.domain.model.Choice
import blackjack.domain.model.Dealer
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Cards()
        val participants = Participants(Dealer(), inputView.readPlayerNames())
        initialDeal(deck, participants)
        printInitialDeal(participants)
        participants.filterPlayers().forEach { player -> playHand(player, deck) }
        processDealerHits(deck, participants.findDealer())
        announceResult(participants)
    }

    private fun initialDeal(
        deck: Cards,
        participants: Participants,
    ) {
        participants.players.forEach { player ->
            player.accept(deck.draw(START_CARD_COUNT))
        }
    }

    private fun printInitialDeal(participants: Participants) {
        outputView.printInitialDeals(participants)
        outputView.printPlayersStatus(participants)
    }

    private fun playHand(
        player: Player,
        deck: Cards,
    ) {
        if (!player.isBust()) return
        val choice = Choice(inputView.readPlayerAction(player))
        if (!choice.isHit()) {
            printStatusOnNoHit(player)
            return
        }
        player.accept(deck.draw())
        outputView.printPlayerStatus(player)
        playHand(player, deck)
    }

    private fun printStatusOnNoHit(player: Player) {
        if (player.showCards().count() == START_CARD_COUNT) outputView.printPlayerStatus(player)
    }

    private fun processDealerHits(
        deck: Cards,
        dealer: Dealer,
    ) {
        while (dealer.getScore() <= Dealer.DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.accept(deck.draw())
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
}
